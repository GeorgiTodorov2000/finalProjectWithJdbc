//package dao.impl;
//
//import dao.RecipeRepository;
//import exception.NonexistingEntityException;
//import model.Recipe;
//
//import java.util.*;
//
//public class RecipeRepositoryMemoryImpl implements RecipeRepository {
//    private Map<Long, Recipe> recipes = new HashMap<Long, Recipe>(); // O(1) find by ID
//
//    @Override
//    public List<Recipe> findAll() {
//        List<Recipe> list = new ArrayList<Recipe>(recipes.values());
//        return list;
//    }
//
//    //TODO
//    @Override
//    public List<Recipe> findAllSorted(Comparator<Recipe> comparator) {
//        return null;
//    }
//
//
//    @Override
//    public Recipe findById(Long id) {
//        return recipes.get(id);
//    }
//
//    @Override
//    public Recipe create(Recipe restaurant) {
//        recipes.put(restaurant.getId(), restaurant);
//        return restaurant;
//    }
//
//    @Override
//    public void addAll(Collection<Recipe> entities) {
//
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public Recipe update(Recipe recipe) throws NonexistingEntityException {
//        Recipe old = findById(recipe.getId());
//        if(old == null) {
//            throw new NonexistingEntityException("User with ID='" + recipe.getId() + "' does not exist.");
//        }
//        recipes.put(recipe.getId(), recipe);
//        return recipe;
//    }
//
//    @Override
//    public Recipe deleteById(Long id) throws NonexistingEntityException {
//        Recipe old = recipes.remove(id);
//        if(old == null) {
//            throw new NonexistingEntityException("User with ID='" + id + "' does not exist.");
//        }
//        return old;
//    }
//
//    @Override
//    public long count() {
//        return recipes.size();
//    }
//
//    @Override
//    public Recipe findById(long id) {
//        return recipes.get(id);
//    }
//}
