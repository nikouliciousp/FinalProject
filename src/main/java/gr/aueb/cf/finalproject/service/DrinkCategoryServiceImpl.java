package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.dto.DrinkCategoryDto;
import gr.aueb.cf.finalproject.model.DrinkCategory;
import gr.aueb.cf.finalproject.repository.DrinkCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DrinkCategoryServiceImpl implements DrinkCategoryService {

    private final DrinkCategoryRepository drinkCategoryRepository;

    @Autowired
    public DrinkCategoryServiceImpl(DrinkCategoryRepository drinkCategoryRepository) {
        this.drinkCategoryRepository = drinkCategoryRepository;
    }

    @Override
    public DrinkCategory save(DrinkCategoryDto drinkCategoryDto) {
        DrinkCategory drinkCategory = new DrinkCategory(
                drinkCategoryDto.getName()
        );
        return drinkCategoryRepository.save(drinkCategory);
    }

    @Override
    public DrinkCategory getDrinkCategoryById(Long id) {
        return drinkCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<DrinkCategory> getAllDrinkCategories() {
        return drinkCategoryRepository.findAll();
    }

    @Override
    public void updateDrinkCategory(DrinkCategoryDto drinkCategoryDto) {
        DrinkCategory drinkCategory = drinkCategoryRepository.findById(drinkCategoryDto.getId())
                        .orElseThrow (() -> new IllegalArgumentException("Invalid drink category Id: " + drinkCategoryDto.getId()));

        drinkCategory.setName(drinkCategoryDto.getName());
        drinkCategoryRepository.save(drinkCategory);
    }

    @Override
    public boolean deleteDrinkCategoryById(Long id) {
        if (drinkCategoryRepository.existsById(id)) {
            drinkCategoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean isDrinkCategoryExists(String name) {
        DrinkCategory drinkCategory = drinkCategoryRepository.findByName(name);
        return drinkCategory != null;
    }

    @Override
    public DrinkCategory getDrinkCategoryByName(String name) throws UsernameNotFoundException {
        return drinkCategoryRepository.findByName(name);
    }
}
