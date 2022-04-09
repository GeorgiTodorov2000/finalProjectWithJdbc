package model;

public class FoodDelivery {
    private Long foodDeliveryId;
    private Long restaurantId;
    private Long userId;
    private Long recipeId;
    private Long recipe_Id;
    private Long recipe_recipeFromRestaurantId;
    private Long user_Id;
    private String userAddress;
    private int userPhoneNumber;

    public FoodDelivery() {
    }

    public FoodDelivery(Long restaurantId, Long userId, Long recipeId, Long recipe_Id, Long recipe_recipeFromRestaurantId, Long user_Id, String userAddress, int userPhoneNumber) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.recipeId = recipeId;
        this.recipe_Id = recipe_Id;
        this.recipe_recipeFromRestaurantId = recipe_recipeFromRestaurantId;
        this.user_Id = user_Id;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
    }

    public Long getFoodDeliveryId() {
        return foodDeliveryId;
    }

    public void setFoodDeliveryId(Long foodDeliveryId) {
        this.foodDeliveryId = foodDeliveryId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getRecipe_Id() {
        return recipe_Id;
    }

    public void setRecipe_Id(Long recipe_Id) {
        this.recipe_Id = recipe_Id;
    }

    public Long getRecipe_recipeFromRestaurantId() {
        return recipe_recipeFromRestaurantId;
    }

    public void setRecipe_recipeFromRestaurantId(Long recipe_recipeFromRestaurantId) {
        this.recipe_recipeFromRestaurantId = recipe_recipeFromRestaurantId;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(int userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "FoodDelivery{" +
                "foodDeliveryId=" + foodDeliveryId +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", recipeId=" + recipeId +
                ", recipe_Id=" + recipe_Id +
                ", recipe_recipeFromRestaurantId=" + recipe_recipeFromRestaurantId +
                ", user_Id=" + user_Id +
                ", userAddress='" + userAddress + '\'' +
                ", userPhoneNumber=" + userPhoneNumber +
                '}';
    }
}
