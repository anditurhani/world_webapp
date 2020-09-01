package it.objectmethod.ww.dao;

import java.util.List;

import it.objectmethod.ww.model.Country;

public interface ICountryDao {

	public List<Country> getCountryListByContinent(String continent);

	public List<String> getAllContinents();

	public List<Country> getAllCountries();

	public String getContinent(String countryCode);

}
