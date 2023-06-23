package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.dto.DrinkDto;
import gr.aueb.cf.finalproject.model.Drink;
import gr.aueb.cf.finalproject.model.DrinkCategory;
import gr.aueb.cf.finalproject.model.User;
import gr.aueb.cf.finalproject.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Drink saveDrink(DrinkDto drinkDto) {
        Drink drink = new Drink();
        drink.setName(drinkDto.getName());
        drink.setPrice(drinkDto.getPrice());
        drink.setDrinkCategoryId(drinkDto.getDrinkCategoryId());

        return drinkRepository.save(drink);
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    @Override
    public Drink getDrinkById(Long id) {
        return drinkRepository.findById(id).orElse(null);
    }

    @Override
    public Drink updateDrink(DrinkDto drinkDto) {
        Drink drink = drinkRepository.findById(drinkDto.getId())
                .orElseThrow (() -> new IllegalArgumentException("Invalid drink Id: " + drinkDto.getId()));

        drink.setName(drinkDto.getName());
        drink.setPrice(drinkDto.getPrice());
        return drinkRepository.save(drink);
    }

    @Override
    public boolean deleteDrink(Long id) {
        if (drinkRepository.existsById(id)) {
            drinkRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Drink> getAllDrinksByCategoryId(Long categoryId) {
        return drinkRepository.findByDrinkCategoryId(categoryId);
    }

    @Override
    public boolean isDrinkExists(String name) {
        Drink drink = drinkRepository.findByName(name);
        return drink != null;
    }

    @Override
    public Drink getDrinkByName(String name) {
        return drinkRepository.findByName(name);
    }
}
