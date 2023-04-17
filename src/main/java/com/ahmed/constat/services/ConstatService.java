package com.ahmed.constat.services;

import com.ahmed.constat.Constat;
import com.ahmed.constat.*;
import com.ahmed.constat.repos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ConstatService {
    private final ConstatRepository constatRepository;

    public List<Map<String, Object>> getAllConstats() {
        List<Constat> constats = constatRepository.findAll();
        List<Map<String, Object>> results = new ArrayList<>();
        for (Constat constat : constats) {
            Map<String, Object> result = new HashMap<>();
            result.put("constat_id", constat.getId());
            result.put("constat_date", constat.getDate());
            result.put("constat_place", constat.getPlace());
            result.put("constat_injuries", constat.getInjuries());
            result.put("constat_material_damage", constat.getMaterialDamage());
            result.put("constat_circumstances", constat.getCircumstances());

            VehiculeInformation vehiculeInformation = constat.getVehiculeInformation();
            result.put("apparent_damage", vehiculeInformation.getApperantDamage());
            result.put("observation", vehiculeInformation.getObservation());
            result.put("initial_choc_point", vehiculeInformation.getInitialChocPoint());
            result.put("signature", vehiculeInformation.getSignature());

            DriverIdentity driverIdentity = vehiculeInformation.getDriverIdentity();
            result.put("driver_last_name", driverIdentity.getDriverLastName());
            result.put("driver_first_name", driverIdentity.getDriverFirstName());
            result.put("driver_address", driverIdentity.getDriverAddress());
            result.put("licence_number", driverIdentity.getLicenceNumber());
            result.put("licence_date", driverIdentity.getLicenceDate());

            InsuredInformation insuredInformation = vehiculeInformation.getInsuredInformation();
            result.put("insured_last_name", insuredInformation.getInsuredLastName());
            result.put("insured_first_name", insuredInformation.getInsuredFirstName());
            result.put("insured_address", insuredInformation.getInsuredAddress());
            result.put("insured_phone", insuredInformation.getInsuredPhone());

            InsurenceCompanyInformation insuranceCompanyInformation = vehiculeInformation.getInsurenceCompanyInformation();
            result.put("insurance_company_name", insuranceCompanyInformation.getInsurenceCompanyName());
            result.put("insurance_company_number", insuranceCompanyInformation.getInsurenceCompanyNumber());
            result.put("agency_name", insuranceCompanyInformation.getAgencyName());
            result.put("begin_date", insuranceCompanyInformation.getBeginDate());
            result.put("end_date", insuranceCompanyInformation.getEndDate());

            Witness witness = constat.getWitness();
            result.put("witness_full_name", witness.getWitnessFullName());
            result.put("witness_address", witness.getWitnessAddress());
            result.put("witness_phone", witness.getWitnessPhone());

            results.add(result);
        }
        return results;
    }

    public List<Map<String, Object>> getConstatsByUsername(String username) {
        List<Constat> constats = constatRepository.findByUserUsername(username);
        List<Map<String, Object>> results = new ArrayList<>();
        for (Constat constat : constats) {
            Map<String, Object> result = new HashMap<>();
            result.put("constat_id", constat.getId());
            result.put("constat_date", constat.getDate());
            result.put("constat_place", constat.getPlace());
            result.put("constat_injuries", constat.getInjuries());
            result.put("constat_material_damage", constat.getMaterialDamage());
            result.put("constat_circumstances", constat.getCircumstances());

            VehiculeInformation vehiculeInformation = constat.getVehiculeInformation();
            result.put("apparent_damage", vehiculeInformation.getApperantDamage());
            result.put("observation", vehiculeInformation.getObservation());
            result.put("initial_choc_point", vehiculeInformation.getInitialChocPoint());
            result.put("signature", vehiculeInformation.getSignature());

            DriverIdentity driverIdentity = vehiculeInformation.getDriverIdentity();
            result.put("driver_last_name", driverIdentity.getDriverLastName());
            result.put("driver_first_name", driverIdentity.getDriverFirstName());
            result.put("driver_address", driverIdentity.getDriverAddress());
            result.put("licence_number", driverIdentity.getLicenceNumber());
            result.put("licence_date", driverIdentity.getLicenceDate());

            InsuredInformation insuredInformation = vehiculeInformation.getInsuredInformation();
            result.put("insured_last_name", insuredInformation.getInsuredLastName());
            result.put("insured_first_name", insuredInformation.getInsuredFirstName());
            result.put("insured_address", insuredInformation.getInsuredAddress());
            result.put("insured_phone", insuredInformation.getInsuredPhone());

            InsurenceCompanyInformation insuranceCompanyInformation = vehiculeInformation.getInsurenceCompanyInformation();
            result.put("insurance_company_name", insuranceCompanyInformation.getInsurenceCompanyName());
            result.put("insurance_company_number", insuranceCompanyInformation.getInsurenceCompanyNumber());
            result.put("agency_name", insuranceCompanyInformation.getAgencyName());
            result.put("begin_date", insuranceCompanyInformation.getBeginDate());
            result.put("end_date", insuranceCompanyInformation.getEndDate());

            Witness witness = constat.getWitness();
            result.put("witness_full_name", witness.getWitnessFullName());
            result.put("witness_address", witness.getWitnessAddress());
            result.put("witness_phone", witness.getWitnessPhone());

            results.add(result);
        }
        return results;
    }


}
