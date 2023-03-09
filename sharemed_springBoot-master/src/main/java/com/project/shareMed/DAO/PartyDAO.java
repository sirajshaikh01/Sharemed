package com.project.shareMed.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shareMed.entites.Party;


public interface PartyDAO extends JpaRepository<Party, Long> {
	public List<Party> findAllByEmail(String email);
	public Party findByEmail(String email);
	public Party findByEmailAndPassword(String email,String password);
	public Party findById(long partyId);
}
