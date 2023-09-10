package com.Dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.entities.CustomerDetails;
import com.entities.LoanApplicants;
import com.entities.LoanNominee;
import com.entities.LoginClass;
import com.entities.LoginTable;
import com.entities.SessionTable;

@Component
public class DaoImp {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persist(CustomerDetails cust, LoanApplicants lap, LoanNominee nom) {
		em.persist(cust);
		int x = cust.getCust_id();
		System.out.println(x);
		lap.setLapp_cust_id(x);
		em.persist(nom);
		int y = nom.getLnom_id();
		lap.setLapp_lnom_id(y);
		lap.setLapp_dispos_income(lap.getLapp_annual_income() * 0.3);
		lap.setLapp_status("processing");
		em.persist(lap);
	}

	@Transactional
	public List<LoanApplicants> getAllLoanApplicants() {
		return em.createQuery("SELECT la FROM LoanApplicants la").getResultList();
	}

	public LoanApplicants getLoanApplicant(int loanid) {

		try {
			return em.createQuery("SELECT e FROM LoanApplicants e WHERE e.id = :loanid", LoanApplicants.class)
					.setParameter("loanid", loanid).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@Transactional
	public void updateApp(LoanApplicants lap) {
		Query q = em.createQuery(
				"UPDATE LoanApplicants la SET la.lapp_status = :newStatus, la.lapp_conclusion_remarks = :newcon WHERE la.lapp_id = :loanid");
		q.setParameter("newStatus", lap.getLapp_status());
		q.setParameter("newcon", lap.getLapp_conclusion_remarks());
		q.setParameter("loanid", lap.getLapp_id());
		q.executeUpdate();
	}

	public List<LoanApplicants> selOption(String orderByProperty) {
		if (orderByProperty.equals("all")) {
			return em.createQuery("SELECT la FROM LoanApplicants la").getResultList();
		}
		if (orderByProperty.equals("lapp_date")) {

			return em.createQuery("SELECT la FROM LoanApplicants la order by la.lapp_date").getResultList();

		}
		if (orderByProperty.equals("lapp_amount_a")) {

			return em.createQuery("SELECT la FROM LoanApplicants la order by la.lapp_amount asc").getResultList();
		}
		if (orderByProperty.equals("lapp_amount_d")) {
			return em.createQuery("SELECT la FROM LoanApplicants la order by la.lapp_amount desc").getResultList();
		}
		if (orderByProperty.equals("lapp_cust_id")) {
			return em.createQuery("SELECT la FROM LoanApplicants la order by la.lapp_cust_id").getResultList();
		}
		return null;

	}

	public LoginTable checkCredentials(LoginClass log) {

		try {
			return em.createQuery("SELECT e FROM LoginTable e WHERE e.user_email = :email", LoginTable.class)
					.setParameter("email", log.getUserName()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public String createKey(LoginTable ret, SessionTable st) {
		LocalDateTime ldt = LocalDateTime.now();
		st.setUser_id(ret.getUser_id());
		st.setUssn_cdate(ldt);
		st.setUssn_key(generateRandomString(8) + "");
		st.setUssn_exptime(ldt.plusMinutes(30));
		st.setUssn_status("active");
		em.persist(st);
		int id = st.getUssn_id();
		System.out.println(id + "ussn id");
		return st.getUssn_key();
	}

	public static int generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}
		return stringBuilder.toString().hashCode();
	}

}
