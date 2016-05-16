package com.levelup.controller;

import com.levelup.dto.AddressDto;
import com.levelup.dto.PhoneDto;
import com.levelup.model.Address;
import com.levelup.model.Phone;
import com.levelup.service.AddressService;
import com.levelup.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Class {@link com.levelup.controller.AddressController}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

@Controller
@RequestMapping("/address")
public class AddressController {

        @Autowired
        private AddressService addressService;

        @Autowired
        private PhoneService phoneService;


        @RequestMapping(value = "/add", method = RequestMethod.GET)
        public String showMainPage() {
            return "inex";
        }

        @RequestMapping(method = RequestMethod.GET, value = "/create")
        public String showForm() {
            return "create";
        }

        @RequestMapping(value = "/create", method = RequestMethod.POST)
        public String createAddress(@ModelAttribute AddressDto addressDto, Model model) {

            Address address; //= new Address();


            address.setContent(addressDto.getContent());
            address.setCountry(addressDto.getCountry());
            addressService.createAddress(address);
            Phone phone = new Phone();
//            phone.setNamber(phoneDto.getNamber());
            phone.setNamber(addressDto.getPhone());
            phone.setAddress(address);
            phoneService.createPhone(phone);
            List<Phone> list = new ArrayList<Phone>();
/*alexey's code starts*/
            list.add(phone);
/*alexey's code end's*/
            address.setPhones(list);
            addressService.createAddress(address);

//            List<Phone> phones = phoneService.getAllPhone();
            List<Phone> phones = address.getPhones();
            model.addAttribute("content", address.getContent());
            model.addAttribute("country", address.getCountry());
            model.addAttribute("phones", phones);
            return "view";
        }

//        @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//        @ResponseBody
//        public ResponseEntity deleteUser(@PathVariable long id) {
//            userService.deleteById(id);
//            return new ResponseEntity(HttpStatus.OK);
//        }
//
//        @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
//        @ResponseBody
//        public ResponseEntity findUser(@PathVariable long id) {
//            User user = userService.findById(id);
//            if (user == null) {
//                return new ResponseEntity(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity(user, HttpStatus.OK);
//        }
//
//
//        @RequestMapping(value = "/getAll", method = RequestMethod.GET)
//        public ResponseEntity getAllUsers() {
//            List<User> users = userService.getAllUsers();
//            if (CollectionUtils.isEmpty(users)) {
//                return new ResponseEntity(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity(users, HttpStatus.OK);
//        }

}