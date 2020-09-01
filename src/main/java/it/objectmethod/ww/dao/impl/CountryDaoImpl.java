package it.objectmethod.ww.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.objectmethod.ww.dao.ICountryDao;
import it.objectmethod.ww.model.Country;

@Service
public class CountryDaoImpl extends NamedParameterJdbcDaoSupport implements ICountryDao {

	@Autowired
	public CountryDaoImpl(DataSource dataSource) {
		super();
		setDataSource(dataSource);
	}

	public List<Country> getCountryListByContinent(String continent) {
		String sql = "SELECT name name, code code, population population, continent contient FROM country WHERE continent = ?";
		BeanPropertyRowMapper<Country> rm = new BeanPropertyRowMapper<Country>(Country.class);
		List<Country> countryList = getJdbcTemplate().query(sql, new Object[] { continent }, rm);
		return countryList;
	}

	public List<String> getAllContinents() {
		String sql = "SELECT DISTINCT continent continent FROM country";
		List<String> continentList = getJdbcTemplate().queryForList(sql, String.class);
		return continentList;
	}

	public List<Country> getAllCountries() {
		String sql = "SELECT name name, code code, population population, continent continent FROM country";
		BeanPropertyRowMapper<Country> rm = new BeanPropertyRowMapper<Country>(Country.class);
		List<Country> countryList = getJdbcTemplate().query(sql, rm);
		return countryList;
	}

	public String getContinent(String countryCode) {
		String sql = "SELECT continent continent FROM country WHERE code = ?";
		String continent = getJdbcTemplate().queryForObject(sql, new Object[] { countryCode }, String.class);
		return continent;
	}

}
