package com.ghilly.service;

import com.ghilly.model.dao.TravelDiaryEntity;
import com.ghilly.repository.CityRepository;
import com.ghilly.repository.TravelDiaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


public class TravelDiaryServiceRest implements TravelDiaryService {
    private static final Logger logger = LoggerFactory.getLogger(TravelDiaryServiceRest.class);
    private final CityRepository cityRepository;
    private final TravelDiaryRepository travelDiaryRepository;

    public TravelDiaryServiceRest(CityRepository cityRepository, TravelDiaryRepository travelDiaryRepository) {
        this.cityRepository = cityRepository;
        this.travelDiaryRepository = travelDiaryRepository;
    }

    @Override
    public TravelDiaryEntity create(TravelDiaryEntity travelDiaryEntity) {
        logger.info("Transferring data {} to the repository", travelDiaryEntity);
        return travelDiaryRepository.save(travelDiaryEntity);
    }

    @Override
    public TravelDiaryEntity getTravelDiaryEntityById(int id) {
        TravelDiaryEntity travelDiaryEntity = travelDiaryRepository.findById(id).orElseThrow();
        logger.info("Got the record from diary by id [{}]", id);
        return travelDiaryEntity;
    }

    @Override
    public Set<TravelDiaryEntity> getAll() {
        logger.info("Getting data from the repository.");
        return travelDiaryRepository.findAll();
    }

    @Override
    public void delete(int id) {
        travelDiaryRepository.deleteById(id);
        logger.info("Getting data from the repository.");
    }

    @Override
    public TravelDiaryEntity update(TravelDiaryEntity travelDiaryEntity) {
        logger.info("Transferring data {} to the repository", travelDiaryEntity);
        TravelDiaryEntity oldTravelDiaryEntity = travelDiaryRepository.findById(travelDiaryEntity.getId()).orElseThrow();
        oldTravelDiaryEntity.setArrivalDate(travelDiaryEntity.getArrivalDate());
        oldTravelDiaryEntity.setDepartureDate(travelDiaryEntity.getDepartureDate());
        oldTravelDiaryEntity.setRating(travelDiaryEntity.getRating());
        oldTravelDiaryEntity.setRealBudget(travelDiaryEntity.getRealBudget());
        oldTravelDiaryEntity.setPlannedBudget(travelDiaryEntity.getPlannedBudget());
        oldTravelDiaryEntity.setDescription(travelDiaryEntity.getDescription());
        return travelDiaryRepository.save(oldTravelDiaryEntity);
    }

    public boolean travelIdExists(int id) {
        return travelDiaryRepository.findById(id).isPresent();
    }

}
