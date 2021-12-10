import {
    atmosphere,
    clear_day,
    clouds_day,
    clear_night,
    clouds_night,
    rain,
    snow,
    thunderstorm
  } from '../assets/index';
  

export default function getCorrectWeatherImage(weather, isDaytime) {
    function getRndInteger(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    switch (weather) {
        case "Clear":
            return isDaytime? clear_day[getRndInteger(0, clear_day.length - 1)] : clear_night[getRndInteger(0, clear_night.length - 1)];
        case "Clouds":
            return isDaytime? clouds_day[getRndInteger(0, clouds_day.length - 1)] : clouds_night[getRndInteger(0, clouds_night.length - 1)];
        case "Rain":
            return rain[getRndInteger(0, rain.length - 1)];
        case "Drizzle":
            return rain[getRndInteger(0, rain.length - 1)];
        case "Snow":
            return snow[getRndInteger(0, snow.length - 1)];
        case "Thunderstorm":
            return thunderstorm[getRndInteger(0, thunderstorm.length - 1)];
        default:
            return atmosphere[getRndInteger(0, atmosphere.length - 1)];
    }
}