package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Domain.Contract;



public interface ContractService {

	public void createcontract(Contract cont);
	public void updatecontract(Contract contract);
	public void deletecontract(int id);
	public Contract findByid(int id);
	public Contract findByUuid(String uuid);
	public List<Contract> findAll();
	public Contract findAllbyContract(long id);
	public byte[] ContractDetailsPDF(Contract ct) ;
}
