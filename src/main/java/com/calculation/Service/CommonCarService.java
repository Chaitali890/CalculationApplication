package com.calculation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculation.Entity.ChangePlan;
import com.calculation.Entity.GoldCheck;
import com.calculation.Entity.LightBill;
import com.calculation.Entity.LightRate;
import com.calculation.Entity.RechargePlan;
import com.calculation.Repository.ChangePlanRepository;
import com.calculation.Repository.GoldCheckRepository;
import com.calculation.Repository.LightBillRepository;
import com.calculation.Repository.LightRateRepository;
import com.calculation.Repository.RechargePlanRepository;
import com.calculation.Request.AddChangePlanRequest;
import com.calculation.Request.AddGoldCheckRequest;
import com.calculation.Request.AddLightBillRequest;
import com.calculation.Request.AddLightRateRequest;
import com.calculation.Request.AddRechargePlanRequest;
import com.calculation.Request.CheckBillRequest;
import com.calculation.Request.CheckGoldRequest;
import com.calculation.Response.DisplayChangePlanFinalResponse;
import com.calculation.Response.DisplayChangePlanResponse;
import com.calculation.Response.DisplayElectricityBillResponse;
import com.calculation.Response.DisplayGoldCheckResponse;




@Service
public class CommonCarService {

	
	
	@Autowired
	private ChangePlanRepository changePlanRepository;
	
	@Autowired
	private RechargePlanRepository rechargePlanRepository;
	
	@Autowired
	private GoldCheckRepository goldCheckRepository;
	
	@Autowired
	private LightRateRepository lightRateRepository;
	
	@Autowired
	private LightBillRepository lightBillRepository;
	
	
	
	// ---------------------------- Plan Service Method starts from here----------------------------//
	
	public Optional<RechargePlan> addPlanToDatabase(AddRechargePlanRequest request){
		
		RechargePlan plan = null;
		
		if(request.getId()!=null)
		{
			Optional<RechargePlan> plans = rechargePlanRepository.findById(request.getId());
			if(plans.isPresent())
			{
				plan = plans.get();
			}
			else
			{
				return Optional.empty();
			}
		}else
		{
			if(plan==null)
			{
				plan = new RechargePlan();
			}
		}
		
		plan.setPlan(request.getPlan());
		plan.setYear(request.getYear());
		plan.setPrice(request.getPrice());
		
		RechargePlan savedPlan = rechargePlanRepository.save(plan);
		return Optional.ofNullable(savedPlan);
	}
	
	
	public Optional<ChangePlan> saveChangePlan(AddChangePlanRequest request){
		ChangePlan  change = null;
		if(request.getId()!=null)
		{
			Optional<ChangePlan> plans = changePlanRepository.findById(request.getId());
			if(plans.isPresent())
			{
				change = plans.get();
			}
			else
			{
				return Optional.empty();
			}
			
		}
		else
		{
			if(change==null)
			{
				change = new ChangePlan();
				
			}
		}
		if(request.getRechargePlanId()!=null) {
			Optional<RechargePlan> plan = rechargePlanRepository.findById(request.getRechargePlanId());
			if(plan.isPresent())
			{
				RechargePlan newPlan = plan.get();
				change.setRechargePlanId(plan.get().getId());
				int i = 0;
				if(newPlan.getId()>1)
				{
					Double updatePrice = newPlan.getPrice()+((5*newPlan.getId())-5);
					change.setNewPrice(updatePrice);
				}
				else
				{
					Double updatePrice = newPlan.getPrice();
					change.setNewPrice(updatePrice);
				}
			}
			else
			{
				return Optional.empty();
			}
		}
		else
		{
			change.setRechargePlanId(null);
		}

		change.setNewYear(request.getNewYear());
		ChangePlan savedchangePlan =  changePlanRepository.save(change);
		return Optional.ofNullable(savedchangePlan);
		
	}
	
	
	public DisplayChangePlanFinalResponse displayChangePlan() {
		
		DisplayChangePlanFinalResponse finalResponse = new DisplayChangePlanFinalResponse();
		
		List<DisplayChangePlanResponse> response = new ArrayList<>();
		
		List<ChangePlan> changePlanList = changePlanRepository.findAll();
		
		if(changePlanList!=null)
		{
			for(ChangePlan changePlan:changePlanList) {

				DisplayChangePlanResponse changeResponse = new DisplayChangePlanResponse();
				Optional<RechargePlan> plan = rechargePlanRepository.findById(changePlan.getRechargePlanId());
				if(plan.isPresent())
				{
					RechargePlan rechargePlan = plan.get();
					changeResponse.setPlan(rechargePlan.getPlan());
					changeResponse.setYear(rechargePlan.getYear());
					changeResponse.setOldprice(rechargePlan.getPrice());
				}
				else
				{
					throw new IllegalArgumentException("RechargePlan is not found"+changePlan.getRechargePlanId());
				}
				
				changeResponse.setNewYear(changePlan.getNewYear());
				changeResponse.setNewPrice(changePlan.getNewPrice());
				
				response.add(changeResponse);
				finalResponse.setResponse(response);
				
			}
		}
		
			return finalResponse;
	}
	
	
	//-------------------------------------------------Gold Check-------------------------------------------------------//
	
	
	public Optional<GoldCheck> addGoldToDatabase(AddGoldCheckRequest request){
		
		GoldCheck check = null;
		
		
			if(check == null)
			{
				check = new GoldCheck();
				
			}
			else
			{
				Optional<GoldCheck> checkGold = goldCheckRepository.findById(request.getId());
				
				if(checkGold.isPresent())
				{
					GoldCheck checks = checkGold.get();
				}
				else
				{
					return Optional.empty();
				}
			}

	
	
	
		check.setMonth(request.getMonth());
		check.setPrice(request.getPrice());
		check.setYear(request.getYear());
		check.setGram(request.getGram());
		check.setMiligram(request.getMiligram());
		
		GoldCheck checks = goldCheckRepository.save(check);
		return Optional.ofNullable(checks);
	}	
	
	
	public List<DisplayGoldCheckResponse> displayGoldRate(CheckGoldRequest request)
	{
		List<DisplayGoldCheckResponse> finalResponse = new ArrayList<>();
		
		GoldCheck checkExisting = goldCheckRepository.findFirstByIsDeletedFalse();
		
		if(checkExisting == null) {
			
			throw new IllegalArgumentException("no data found with gold");
		}
		
		DisplayGoldCheckResponse response = new DisplayGoldCheckResponse();
		
		//calculate price per gram
		double pricePerGram = checkExisting.getPrice() / checkExisting.getGram();
		
		double gramsFromMilligrams = pricePerGram / checkExisting.getMiligram();
		
		
		double finalMilligram = gramsFromMilligrams * request.getGrams();
		
		double totalPrice = (pricePerGram * request.getGrams()) + finalMilligram;
		
		response.setMonth(checkExisting.getMonth());
		response.setYear(checkExisting.getYear());
		response.setGram(checkExisting.getGram());
		response.setPrice(checkExisting.getPrice());
		response.setEnterGram(request.getGrams());
		response.setEnterMilligram(request.getMilligrams());
		response.setPricebasedOnRate(totalPrice);
		finalResponse.add(response);
		
		return finalResponse;
	}
	
	
	//--------------------------------------------Light Bill----------------------------------------------------------//
	
	
	public Optional<LightRate> addLightrate(AddLightRateRequest request){
		
		LightRate rate = null;
		
		if(request.getId()!=null)
		{
			Optional<LightRate> rates = lightRateRepository.findById(request.getId());
			if(rates.isPresent())
			{
				LightRate rate1 = rates.get();
			}
			else
			{
				return Optional.empty();
			}
		}
		else
		{
			if(rate == null)
			{
				rate = new LightRate();
			}
		}
		
		rate.setUpperBound(request.getUpperBound());
		rate.setLowerBound(request.getLowerBound());
		rate.setLightRateInRupees(request.getLightRateInRupees());
		rate.setFuleAdjustmentComeInRupees(request.getFuleAdjustmentComeInRupees());
		
		LightRate saveRate = lightRateRepository.save(rate);
		return Optional.ofNullable(saveRate);
		
	}
	
	public Optional<LightBill> saveLightBill(AddLightBillRequest request){
		
		LightBill bill = null;
		
		if(request.getId()!=null)
		{
			Optional<LightBill> bills = lightBillRepository.findById(request.getId());
			if(bills.isPresent())
			{
				bill = bills.get();
			}
			else
			{
				return Optional.empty();
			}
		}
		else
		{
			if(bill==null)
			{
				bill = new LightBill();
			}
		}
		
		bill.setRental(request.getRental());
		bill.setDistributionCharges(request.getDistributionCharges());
		bill.setElectricityTax(request.getElectricityTax());
		bill.setElectricitySellTax(request.getElectricitySellTax());
		bill.setPreviousBillCredit(request.getPreviousBillCredit());
		bill.setOtherCharges(request.getOtherCharges());
		bill.setCapacitorPenalty(request.getCapacitorPenalty());
		bill.setAdjustment(request.getAdjustment());
		bill.setRefundingOutstanding(request.getRefundingOutstanding());
		bill.setTotalRefunding(request.getTotalRefunding());

		LightBill savedBill = lightBillRepository.save(bill);
		return Optional.ofNullable(savedBill);
	
		
	}
	
	
//	public List<DisplayElectricityBillResponse> displayElectricityBill(CheckBillRequest request) {
//		
//		List<DisplayElectricityBillResponse> finalResponse = new ArrayList<>();
//		
//		List<LightBill> lightBills = lightBillRepository.findAll();
//		
//			if(lightBills == null) {
//			
//			throw new IllegalArgumentException("no data found with gold");
//				}
//			
//			
//			
//			DisplayElectricityBillResponse response = new DisplayElectricityBillResponse();
//			response.setRental(lightBills.getRental());
//			
//			
//			List<LightRate> rate = lightRateRepository.findAll();
//			
//			if(rate == null) {
//				throw new IllegalArgumentException("no data found with light rate table");
//			}
//		
//			Integer unit = request.getUnit();
//			
//			
//			
//			
//			if(unit>rate.getLowerBound()&& unit<rate.getUpperBound())
//			{
//				double electicityChargesU = unit * rate.getLightRateInRupees();
//				response.setElecticityCharges(electicityChargesU);
//			}
//			
//			else if(unit>rate.getLowerBound()&& unit<rate.getUpperBound())
//			{double electicityChargesU = unit * rate.getLightRateInRupees();
//				response.setElecticityCharges(electicityChargesU);		
//			}
//			
//			else if(unit>rate.getLowerBound()&& unit<rate.getUpperBound())
//			{double electicityChargesU = unit * rate.getLightRateInRupees();
//				response.setElecticityCharges(electicityChargesU);		
//			}
//			
//			else if(unit>rate.getLowerBound()&& unit<rate.getUpperBound())
//			{double electicityChargesU = unit * rate.getLightRateInRupees();
//				response.setElecticityCharges(electicityChargesU);		
//			}
//			else if(unit>rate.getLowerBound())
//			{double electicityChargesU = unit * rate.getLightRateInRupees();
//				response.setElecticityCharges(electicityChargesU);			
//			}
//			else
//			{
//			    throw new IllegalArgumentException("Invalid rate bounds: lower=" + rate.getLowerBound() + ", upper=" + rate.getUpperBound());
//
//			}
//			
//			double distributionChargesU = unit * lightBills.getDistributionCharges();
//			
//			response.setDistributionCharges(distributionChargesU);
//			
//			double fuelAdjustmentChargesU = unit * rate.getFuleAdjustmentComeInRupees();
//			
//			if(rate.getLowerBound()>0 && rate.getUpperBound()<100)
//			{
//				response.setFuleAdjustmentCharges(fuelAdjustmentChargesU);
//			}
//			
//			else if(rate.getLowerBound()>101 && rate.getUpperBound()<300)
//			{
//				response.setFuleAdjustmentCharges(fuelAdjustmentChargesU);			
//			}
//			
//			else if(rate.getLowerBound()>301 && rate.getUpperBound()<500)
//			{
//				response.setFuleAdjustmentCharges(fuelAdjustmentChargesU);			
//			}
//			
//			else if(rate.getLowerBound()>501 && rate.getUpperBound()<1000)
//			{
//				response.setFuleAdjustmentCharges(fuelAdjustmentChargesU);		
//			}
//			else if(rate.getLowerBound()>1000)
//			{
//				response.setFuleAdjustmentCharges(fuelAdjustmentChargesU);			
//			}
//			else
//			{
//			    throw new IllegalArgumentException("Invalid rate bounds: lower=" + rate.getLowerBound() + ", upper=" + rate.getUpperBound());
//
//			}
//			
//			double addition = unit  + fuelAdjustmentChargesU + distributionChargesU;
//			
//			double fuelAdjustmentCommit = addition*lightBills.getFuleAdjustmentCharges()/100;
//			
//			response.setFuleAdjustmentCharges(fuelAdjustmentCommit);
//			
//			response.setElectricitySellTax(lightBills.getElectricitySellTax());
//			response.setPreviousBillCredit(lightBills.getPreviousBillCredit());
//			response.setOtherCharges(lightBills.getOtherCharges());
//			
//			double currentInterest = addition + fuelAdjustmentCommit;
//			
//			response.setCurrentInterest(currentInterest);
//			
//			response.setCapacitorPenalty(lightBills.getCapacitorPenalty());
//			response.setAdjustment(lightBills.getAdjustment());
//			
//			response.setRefundingOutstanding(lightBills.getRefundingOutstanding());
//			
//			double totalRefunding = lightBills.getCapacitorPenalty() - lightBills.getAdjustment();
//			response.setTotalRefunding(totalRefunding);
//			
//			double netBillAmount = currentInterest + totalRefunding;
//			response.setNetBillAmount(netBillAmount);
//			
//			double roundedBill = Math.round(netBillAmount);
//			
//			String formattedBill = String.format("%.2f", roundedBill);
//			
//			response.setRoundedBill(formattedBill);
//			
//			
//			finalResponse.add(response);
//			return finalResponse;
// 
//	}
	
	
	public List<DisplayElectricityBillResponse> displayElectricityBill(CheckBillRequest request) {
	    List<DisplayElectricityBillResponse> finalResponse = new ArrayList<>();
	    List<LightBill> lightBills = lightBillRepository.findAll();
	    
	    if (lightBills.isEmpty()) {
	        throw new IllegalArgumentException("No data found in LightBill repository.");
	    }

	    LightBill lightBill = lightBills.get(0); // Assuming there's only one LightBill
	    DisplayElectricityBillResponse response = new DisplayElectricityBillResponse();
	    response.setRental(lightBill.getRental());

	    List<LightRate> rates = lightRateRepository.findAll();
	    if (rates.isEmpty()) {
	        throw new IllegalArgumentException("No data found in LightRate repository.");
	    }

	    Integer unit = request.getUnit();
	    double electricityCharges = 0.0;
	    double fuelAdjustmentCharges = 0.0;

	    for (LightRate rate : rates) {
	        if (unit >= rate.getLowerBound() && unit <= rate.getUpperBound()) {
	            electricityCharges = unit * rate.getLightRateInRupees();
	            fuelAdjustmentCharges = unit * rate.getFuleAdjustmentComeInRupees();
	            break; // Exit loop once the correct rate is found
	        }
	    }

	    if (electricityCharges == 0.0) {
	        throw new IllegalArgumentException("Invalid unit count: " + unit + 
	            ". Ensure it falls within defined rate boundaries.");
	    }

	    double distributionCharges = unit * lightBill.getDistributionCharges();
	    response.setElecticityCharges(electricityCharges);
	    response.setDistributionCharges(distributionCharges);
	   response.setFuleAdjustmentCharges(fuelAdjustmentCharges);

	    double addition = unit + electricityCharges+fuelAdjustmentCharges + distributionCharges;
	    double fuelAdjustmentCommit = addition * lightBill.getElectricityTax() / 100;
	    //response.setFuleAdjustmentCharges(fuelAdjustmentCommit);
	    response.setElectricityTax(fuelAdjustmentCommit);

	    double currentInterest = addition + fuelAdjustmentCommit;
	    response.setCurrentInterest(currentInterest);
	    response.setElectricitySellTax(lightBill.getElectricitySellTax());
	    response.setPreviousBillCredit(lightBill.getPreviousBillCredit());
	    response.setOtherCharges(lightBill.getOtherCharges());

	    response.setCapacitorPenalty(lightBill.getCapacitorPenalty());
	    response.setAdjustment(lightBill.getAdjustment());
	    response.setRefundingOutstanding(lightBill.getRefundingOutstanding());

	    double totalRefunding = lightBill.getCapacitorPenalty() - lightBill.getAdjustment();
	    response.setTotalRefunding(totalRefunding);

	    double netBillAmount = currentInterest + totalRefunding;
	    response.setNetBillAmount(netBillAmount);
	   // response.setRoundedBill(String.format("%.2f", Math.round(netBillAmount)));

	    finalResponse.add(response);
	    return finalResponse;
	}

	
	
}
