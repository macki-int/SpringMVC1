package pl.sda;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarsController {
    List<Car> cars = new ArrayList<Car>();

    public CarsController() {
        cars.add(new Car(1, "Aston Martin", 250));
        cars.add(new Car(2, "Bentley", 300));
        cars.add(new Car(3, "Citroen", 150));
    }


    @GetMapping("/view")
    public ModelAndView getCarsView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars");
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping
    @ResponseBody
    List<Car> getCars() {
        return cars;
    }

    @GetMapping(value = "/marks")
    @ResponseBody
    public ResponseEntity<List<Car>> getCarsByMark(@RequestParam(value = "mark", required = true, defaultValue = "BMW") String mark) {
        List<Car> carList = cars.stream()
                .filter(car -> car.getMark().equals(mark))
                .collect(Collectors.toList());
        return ResponseEntity.ok(carList);
    }

    @GetMapping(value = "/power")
    @ResponseBody
    public ResponseEntity<List<Car>> getCarsByMarkAndPower(
            @RequestParam(value = "mark", required = true, defaultValue = "BMW") String mark,
            @RequestParam(value = "power", required = false) int power) {
        List<Car> carList = cars.stream()
                .filter(car -> car.getMark().equals(mark))
                .filter(car -> car.getPower() == power)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carList);
    }

    @PostMapping("/cars")
    @ResponseBody (HttpStatus.OK)
    void addNewCar (@RequestBody Car car){
        //TODO add form to imput parameters
        return new Car(4, "BMW", 180);
    }



    @GetMapping("/{mark}")
    List<Car> getCarByMarkPath(@PathVariable("mark") String mark){
        return null;
    }


}
