package it.objectmethod.ww.dao;

import java.util.List;

import it.objectmethod.ww.model.City;

public interface ICityDao {

	public List<City> getCityListByCountryCode(String countryCode);

	public List<City> searchCities(String searchStr, String countryCodeStr);

	public void addCity(City city);

	public void updateCity(City city);

	public City getCityById(int id);

}
