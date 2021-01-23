package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> filteredResults = new ArrayList<>();
        Map<String, Integer> map = mapCaloriesPerDay(meals);
        for (UserMeal meal : meals) {
            LocalDateTime date = meal.getDateTime();
            if (date.toLocalTime().isAfter(startTime) && date.toLocalTime().isBefore(endTime)) {
                String dateStr = date.toLocalDate().format(DateTimeFormatter.BASIC_ISO_DATE);
                int dateCal = map.getOrDefault(dateStr, 0);
                filteredResults.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), dateCal <= caloriesPerDay));
            }
        }
        return filteredResults;
    }

    private static Map<String, Integer> mapCaloriesPerDay(List<UserMeal> meals) {
        Map<String, Integer> caloriesPerDay = new HashMap<>();
        for (UserMeal item : meals) {
            LocalDate date = item.getDateTime().toLocalDate();
            String dateString = date.format(DateTimeFormatter.BASIC_ISO_DATE);
            int calories = item.getCalories();
            caloriesPerDay.put(dateString, caloriesPerDay.getOrDefault(dateString, 0) + calories);
        }
        return caloriesPerDay;
    }


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
