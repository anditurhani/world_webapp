package it.objectmethod.ww.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.objectmethod.ww.dao.ICityDao;
import it.objectmethod.ww.model.City;

@Service
public class CityDaoImpl extends NamedParameterJdbcDaoSupport implements ICityDao {

	@Autowired
	public CityDaoImpl(DataSource dataSource) {
		super();
		setDataSource(dataSource);
	}

	public List<City> getCityListByCountryCode(String countryCode) {
		String sql = "SELECT id id, name name, population population, countrycode countrycode FROM city WHERE countrycode = ?";
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		List<City> cityList = getJdbcTemplate().query(sql, new Object[] { countryCode }, rm);
		return cityList;
	}

	public List<City> searchCities(String searchStr, String countrycode) {
		String sql = "SELECT id id, name name, population population, countrycode countrycode FROM city WHERE name LIKE :searchStr AND ('' = :givencode OR countrycode = :givencode)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("searchStr", searchStr);
		params.addValue("code", countrycode);
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		List<City> cityList = getNamedParameterJdbcTemplate().query(sql, params, rm);
		return cityList;
	}

	public void addCity(City city) {
		String sql = "INSERT INTO city (name, population, countrycode) VALUES (:name, :population, :givencode)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", city.getName());
		params.addValue("population", city.getPopulation());
		params.addValue("givencode", city.getCountryCode());
		getNamedParameterJdbcTemplate().update(sql, params);
	}

	public void updateCity(City city) {
		String sql = "UPDATE city SET name = :name, population = :population, countrycode = :givencode WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", city.getName());
		params.addValue("population", city.getPopulation());
		params.addValue("givencode", city.getCountryCode());
		params.addValue("id", city.getId());
		getNamedParameterJdbcTemplate().update(sql, params);
	}

	public City getCityById(int id) {
		String sql = "SELECT id id, name name, population population, countrycode countrycode FROM city WHERE id = ?";
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		City city = getJdbcTemplate().queryForObject(sql, new Object[] { id }, rm);
		return city;
	}

}
