package com.ghilly.service;

import com.ghilly.exception.IdNotFoundException;
import com.ghilly.exception.NameAlreadyExistsException;
import com.ghilly.model.City;
import com.ghilly.repository.CityRepository;
import com.ghilly.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ghilly.utils.ValidationUtils.checkNameIsCorrect;

public class CityServiceRest implements CityService{

    private static final Logger logger = LoggerFactory.getLogger(CityServiceRest.class);
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityServiceRest(CityRepository repository, CountryRepository countryRepository) {
        this.cityRepository = repository;
        this.countryRepository = countryRepository;
    }

    public City create(String cityName, int countryId) {
        if (!(countryRepository.findById(countryId).isPresent())) throw new IdNotFoundException("The country with the ID " +
                countryId + " is not found.");
        checkNameIsCorrect(cityName);
        if (cityRepository.findByName(cityName).isPresent())
            throw new NameAlreadyExistsException("The city with the name " + cityName + " already exists!");
        City city = cityRepository.save(new City(cityName, countryId));
        logger.info("The city with the name {} is created, country name is {}", cityName,
                countryRepository.findById(countryId).get().getName());
        return city;
    }
}
