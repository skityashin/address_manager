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
import java.util.Iterator;
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
            Address address = addressService.findByContent(addressDto.getContent());

            if (address == null) {
                address = new Address();
                address.setContent(addressDto.getContent());
                address.setCountry(addressDto.getCountry());
                addressService.createAddress(address);
            }

            Phone phone = new Phone();
            phone.setNamber(addressDto.getPhone());
            phone.setAddress(address);

            try {
                phoneService.createPhone(phone);
            } catch (Exception e) {
//                e.printStackTrace();
                return "error";
            }

            List<Phone> phonesList = address.getPhones();
            boolean exist = false;
            if(phonesList != null){
                Iterator<Phone> iterator = phonesList.iterator();
                while (iterator.hasNext()){
                    Phone next = iterator.next();
                    if(next.getNamber().equals(phone.getNamber())){
                        exist = true;
                        break;
                    }
                }
            }else {
                phonesList = new ArrayList<Phone>();
                phonesList.add(phone);
                address.setPhones(phonesList);
                exist = true;
            }
            if (!exist) {
                phonesList.add(phone);
                addressService.createAddress(address);
            }


            model.addAttribute("content", address.getContent());
            model.addAttribute("country", address.getCountry());
            model.addAttribute("phones", address.getPhones());
            return "view";
        }

        @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
        public String deleteAddress(@ModelAttribute AddressDto addressDto, Model model) {
            Address address = null;
            try {
                address = addressService.findByContent(addressDto.getContent());
                addressService.deleteById(address.getId_address());
            } catch (Exception e) {
                model.addAttribute("content", "\"" + addressDto.getContent() + "\"" +  " <font color=\"#FF0000\">not found!!!</font>");
                return "deleted";
            }
            model.addAttribute("content", "\"" + address.getContent() + "\"" + "  <font color=\"#01DF01\">is deleted!!!</font>");
            return "deleted";
        }



        @RequestMapping(value = "/deletePhone", method = RequestMethod.POST)
        public String deletePhone(@ModelAttribute PhoneDto phoneDto, Model model) {
            Phone phone = null;
            try {
                phone = phoneService.findByNamber(phoneDto.getNamber());
                phoneService.deleteById(phone.getId_phone());
            } catch (Exception e) {
                model.addAttribute("content", "\"" + phoneDto.getNamber() + "\"" +  " <font color=\"#FF0000\">not found!!!</font>");
                return "deleted";
            }
            model.addAttribute("content", "\"" + phoneDto.getNamber() + "\"" + "  <font color=\"#01DF01\">is deleted!!!</font>");
            return "deleted";
        }

        @RequestMapping(value = "/searchAddress", method = RequestMethod.GET)
        public String findAddress(@ModelAttribute AddressDto addressDto, Model model) {
            List<Address> address = addressService.findByContentPartial(addressDto.getContent());
            model.addAttribute("address", address);
            return "addressView";
        }

        @RequestMapping(value = "/searchPhone", method = RequestMethod.GET)
        public String findPhone(@ModelAttribute PhoneDto phoneDto, Model model) {
            List<Phone> phones = phoneService.findByNamberPartial(phoneDto.getNamber());
            model.addAttribute("phones", phones);
            return "phonesView";
        }

}
