package com.levelup.controller;

import com.levelup.dto.AddressDto;
import com.levelup.dto.PhoneDto;
import com.levelup.model.Address;
import com.levelup.model.Phone;
import com.levelup.service.AddressService;
import com.levelup.service.PhoneService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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

    private static final org.apache.log4j.Logger LOG = Logger.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String showForm() {
        BasicConfigurator.configure();
        LOG.debug("create");
        return "create";
    }

    /**
     * Method that create and add an object to the database
     * and return response with error message if something wrong. If everything ok -
     * response with success message
     *
     * @param addressDto
     * @param model
     * @return results page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAddress(@ModelAttribute AddressDto addressDto, Model model) {

        LOG.info("HIIIII");
        Address address = addressService.findByContent(addressDto.getContent());

        if (address == null) {
            address = new Address();
            address.setContent(addressDto.getContent());
            address.setCountry(addressDto.getCountry());
            addressService.createAddress(address);
        }

        Phone phone = new Phone();
        phone.setNumber(addressDto.getPhone());
        phone.setAddress(address);

        try {
            phoneService.createPhone(phone);
        } catch (Exception e) {
            return "error";
        }

        List<Phone> phonesList = address.getPhones();
        boolean exist = false;
        if (phonesList != null) {
            Iterator<Phone> iterator = phonesList.iterator();
            while (iterator.hasNext()) {
                Phone next = iterator.next();
                if (next.getNumber().equals(phone.getNumber())) {
                    exist = true;
                    break;
                }
            }
        } else {
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
        LOG.info("view");
        return "view";
    }

    /**
     * Method that delete an object by id from the database
     * and return response with error message if something wrong. If everything ok -
     * response with success message
     *
     * @param addressDto
     * @param model
     * @return result page
     * *
     */
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
    public String deleteAddress(@ModelAttribute AddressDto addressDto, Model model) {
        try {
            Address address = addressService.findByContent(addressDto.getContent());
            addressService.deleteById(address.getId_address());
        } catch (Exception e) {
            model.addAttribute("content", "\"" + addressDto.getContent() + "\"" + " <font color=\"#FF0000\">not found!!!</font>");
            return "deleted";
        }
        model.addAttribute("content", "\"" + addressDto.getContent() + "\"" + "  <font color=\"#01DF01\">is deleted!!!</font>");
        return "deleted";
    }

    /**
     * Method that delete an object by id from the database
     * and return response with error message if something wrong. If everything ok -
     * response with success message
     *
     * @param phoneDto
     * @param model
     * @return result page
     * *
     */
    @RequestMapping(value = "/deletePhone", method = RequestMethod.POST)
    public String deletePhone(@ModelAttribute PhoneDto phoneDto, Model model) {
        try {
            Phone phone = phoneService.findByNumber(phoneDto.getNumber());
            phoneService.deleteById(phone.getId_phone());
        } catch (Exception e) {
            model.addAttribute("content", "\"" + phoneDto.getNumber() + "\"" + " <font color=\"#FF0000\">not found!!!</font>");
            return "deleted";
        }
        model.addAttribute("content", "\"" + phoneDto.getNumber() + "\"" + "  <font color=\"#01DF01\">is deleted!!!</font>");
        return "deleted";
    }

    /**
     * Method that receives an object by id from the database
     * and return response with error message if something wrong. If everything ok -
     * response with success message
     *
     * @param addressDto
     * @param model
     * @return result page
     * *
     */
    @RequestMapping(value = "/searchAddress", method = RequestMethod.GET)
    public String findAddress(@ModelAttribute AddressDto addressDto, Model model) {
        List<Address> address = addressService.findByContentPartial(addressDto.getContent());
        model.addAttribute("address", address);
        return "addressView";
    }

    /**
     * Method that receives an object by id from the database
     * and return response with error message if something wrong. If everything ok -
     * response with success message
     *
     * @param phoneDto
     * @param model
     * @return result page
     * *
     */
    @RequestMapping(value = "/searchPhone", method = RequestMethod.GET)
    public String findPhone(@ModelAttribute PhoneDto phoneDto, Model model) {
        List<Phone> phones = phoneService.findByNumberPartial(phoneDto.getNumber());
        model.addAttribute("phones", phones);
        return "phonesView";
    }

}
