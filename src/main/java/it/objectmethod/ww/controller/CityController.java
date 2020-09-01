package it.objectmethod.ww.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.ww.dao.ICityDao;
import it.objectmethod.ww.dao.ICountryDao;
import it.objectmethod.ww.model.City;
import it.objectmethod.ww.model.Country;

@Controller
public class CityController {

	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private ICityDao cityDao;

	@GetMapping("/cities")
	public String printCities(@RequestParam("countrycode") String countryCode, Model model) {
		List<City> cityList = cityDao.getCityListByCountryCode(countryCode);
		model.addAttribute("cityList", cityList);
		return "cities";
	}

	@GetMapping("/search")
	public String searchCities(@RequestParam(name = "searchStr") String searchStr,
			@RequestParam(name = "countrycode") String countrycode, ModelMap model) {
		searchStr = "%" + searchStr + "%";
		List<City> cityList = cityDao.searchCities(searchStr, countrycode);
		model.addAttribute("cityList", cityList);
		return "cities";
	}

	@GetMapping("/updateCities")
	public String updateCities(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name,
			@RequestParam(name = "population") int population, @RequestParam(name = "countrycode") String countryCode,
			ModelMap model, HttpSession session) {
		String message = null;
		String continent = null;
		if (population >= 0) {
			City city = new City();
			city.setName(name);
			city.setPopulation(population);
			city.setCountryCode(countryCode);
			if (id == -1) {
				cityDao.addCity(city);
				message = "city add";
			} else {
				city.setId(id);
				cityDao.updateCity(city);
				message = "city updated";
			}
		}

		continent = countryDao.getContinent(countryCode);
		session.setAttribute("continent", continent);
		model.addAttribute("message", message);
		model.addAttribute("countrycode", countryCode);

		return "forward:/cities";
	}

	@GetMapping("/updateForm")
	public String loadUpdateForm(@RequestParam(name = "id") int id, ModelMap model) {
		List<Country> countryList = countryDao.getAllCountries();
		City city = null;
		if (id != -1) {
			city = cityDao.getCityById(id);
			model.addAttribute("id", city.getId());
			model.addAttribute("name", city.getName());
			model.addAttribute("population", city.getPopulation());
			model.addAttribute("countrycode", city.getCountryCode());
		}
		model.addAttribute("countryList", countryList);
		return "updateForm";
	}

}
