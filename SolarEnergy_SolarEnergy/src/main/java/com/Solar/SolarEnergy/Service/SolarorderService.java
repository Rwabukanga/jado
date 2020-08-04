package com.Solar.SolarEnergy.Service;

import java.util.List;

import javax.persistence.criteria.Order;

import com.Solar.SolarEnergy.Domain.Solarorder;

public interface SolarorderService {
	
	public void createorder(Solarorder order);
	public void updateorder(Solarorder order);
	public void deleteorder(int id);
	public Solarorder findByid(int id);
	public Solarorder findByUuid(String uuid);
	public List<Solarorder> findAll();
	public List<Solarorder> findAllbyRegistrar(long id);
	public byte[] OrderDetailsPDF(Solarorder appointment);

}
