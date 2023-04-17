package com.ahmed.controller;

import com.ahmed.constat.*;
import com.ahmed.constat.VehiculeInformation;
import com.ahmed.constat.repos.*;
import com.ahmed.constat.request.ConstatRequest;
import com.ahmed.constat.services.ConstatService;
import com.ahmed.user.User;
import com.ahmed.user.UserDTO;
import com.ahmed.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {

  private final UserService service;

  private final ConstatRepository constatRepository;

  private final CarDescRepository carDescRepository;

  private final DriverIdentityRepository driverIdentityRepository;

  private final InsuredInformationRepository insuredInformationRepository;

  private final InsurenceCompanyInformationRepository insurenceCompanyInformationRepository;

  private final WitnessRepository witnessRepository;

  private final VehiculeInformationRepository vehiculeInformationRepository;


  private final ConstatService constatService  ;
  @GetMapping
  public ResponseEntity<String> test() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

  @GetMapping("/users")
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserDTO> getAll() {
    return service.getAllUsers();
  }

  @PostMapping("/create")
  public ResponseEntity<?> createConstat(@RequestBody ConstatRequest constatRequest, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    VehiculeInformation vehiculeInformation = new VehiculeInformation();
    //Constat
    Constat constat = new Constat();
    constat.setUser(user);
    constat.setVehiculeInformation(vehiculeInformation);
    constat.setDate(constatRequest.getDate());
    constat.setPlace(constatRequest.getPlace());
    constat.setInjuries(constatRequest.isInjuries());
    constat.setMaterialDamage(constatRequest.isMaterialDamage());
    constat.setCircumstances(Circumstances.EN_STATIONNEMENT);
    //Vehicule Information
    vehiculeInformation.setConstat(constat);
    vehiculeInformation.setApperantDamage(constatRequest.getApperantDamage());
    vehiculeInformation.setInitialChocPoint(constatRequest.getInitialChocPoint());
    vehiculeInformation.setObservation(constatRequest.getObservation());
    vehiculeInformation.setSignature(constatRequest.getSignature());
    //Vehicule Descirption
    VehiculeDescription vehiculeDescription = new VehiculeDescription();
    vehiculeDescription.setVehiculeInformation(vehiculeInformation);
    vehiculeDescription.setBrand(constatRequest.getCarBrand());
    vehiculeDescription.setType(constatRequest.getCarType());
    vehiculeDescription.setCarPlate(constatRequest.getCarPlate());
    vehiculeDescription.setDirection(constatRequest.getCarDirection());
    //Driver Identitty
    DriverIdentity driverIdentity = new DriverIdentity();
    driverIdentity.setVehiculeInformation(vehiculeInformation);
    driverIdentity.setDriverLastName(constatRequest.getDriverLastName());
    driverIdentity.setDriverFirstName(constatRequest.getDriverFirstName());
    driverIdentity.setDriverAddress(constatRequest.getDriverAddress());
    driverIdentity.setLicenceNumber(constatRequest.getLicenceNumber());
    driverIdentity.setLicenceDate(constatRequest.getLicenceDate());
    //Insured Information
    InsuredInformation insuredInformation = new InsuredInformation();
    insuredInformation.setVehiculeInformation(vehiculeInformation);
    insuredInformation.setInsuredLastName(constatRequest.getInsuredLastName());
    insuredInformation.setInsuredFirstName(constatRequest.getInsuredFirstName());
    insuredInformation.setInsuredAddress(constatRequest.getInsuredAddress());
    insuredInformation.setInsuredPhone(constatRequest.getInsuredPhone());
    //insurence Company Information
    InsurenceCompanyInformation insurenceCompanyInformation = new InsurenceCompanyInformation();
    insurenceCompanyInformation.setInsurenceCompanyName(constatRequest.getInsurenceCompanyName());
    insurenceCompanyInformation.setInsurenceCompanyNumber(constatRequest.getInsurenceCompanyNumber());
    insurenceCompanyInformation.setAgencyName(constatRequest.getAgencyName());
    insurenceCompanyInformation.setBeginDate(constatRequest.getBeginDate());
    insurenceCompanyInformation.setEndDate(constatRequest.getEndDate());
    insurenceCompanyInformation.setVehiculeInformation(vehiculeInformation);
    //Witness
    Witness witness = new Witness();
    witness.setWitnessFullName(constatRequest.getWitnessFullName());
    witness.setWitnessAddress(constatRequest.getWitnessAddress());
    witness.setWitnessPhone(constatRequest.getWitnessPhone());
    witness.setConstat(constat);

    vehiculeInformationRepository.save(vehiculeInformation);
    constatRepository.save(constat);
    carDescRepository.save(vehiculeDescription);
    driverIdentityRepository.save(driverIdentity);
    insuredInformationRepository.save(insuredInformation);
    insurenceCompanyInformationRepository.save(insurenceCompanyInformation);
    witnessRepository.save(witness);

    return ResponseEntity.ok(HttpStatus.CREATED);
  }

  @GetMapping("/constat/{username}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> getConstatsByUser(@PathVariable("username") String username) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
    if (isAdmin) {
      List<Map<String, Object>> constats = constatService.getConstatsByUsername(username);
      return new ResponseEntity<>(constats, HttpStatus.OK);
    } else
      return new ResponseEntity<>("You Don't Have The Permissions", HttpStatus.FORBIDDEN);
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> getAllConstats() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
    if (isAdmin) {
      List<Map<String, Object>> constats = constatService.getAllConstats();
      return new ResponseEntity<>(constats, HttpStatus.OK);
    } else
      return new ResponseEntity<>("You Don't Have The Permissions", HttpStatus.FORBIDDEN);
  }

  @GetMapping("/constats/{username}")
  @PreAuthorize("#username == authentication.principal.username")
  public ResponseEntity<?> getConstats(@PathVariable String username) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String currentUsername = user.getUsername();
    if (!currentUsername.equals(username)) {
      List<Map<String, Object>> constats = constatService.getConstatsByUsername(username);
      return new ResponseEntity<>(constats, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
