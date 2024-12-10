package com.calculation.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculation.Entity.ChangePlan;
import com.calculation.Entity.GoldCheck;
import com.calculation.Entity.LightBill;
import com.calculation.Entity.LightRate;
import com.calculation.Entity.RechargePlan;
import com.calculation.Request.AddChangePlanRequest;
import com.calculation.Request.AddGoldCheckRequest;
import com.calculation.Request.AddLightBillRequest;
import com.calculation.Request.AddLightRateRequest;
import com.calculation.Request.AddRechargePlanRequest;
import com.calculation.Request.CheckBillRequest;
import com.calculation.Request.CheckGoldRequest;
import com.calculation.Request.CheckGoldRequest;
import com.calculation.Response.DisplayChangePlanFinalResponse;
import com.calculation.Response.DisplayElectricityBillResponse;
import com.calculation.Response.DisplayGoldCheckResponse;
import com.calculation.Service.CommonCarService;



@RestController
@RequestMapping("/common")
public class CommonCarController {

	
	@Autowired
	private CommonCarService commonCarService;
	

	
	//----------------------------------Recharge Plan ---------------------------------------------------------//
	
	@PostMapping("/addPlan")
	public ResponseEntity<Optional<RechargePlan>> savePlanToDatabase(@RequestBody AddRechargePlanRequest request){
		
		Optional<RechargePlan> plan = commonCarService.addPlanToDatabase(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(plan);
		
	}
	
	@PostMapping("/changePlan")
	public ResponseEntity<Optional<ChangePlan>> saveChangePlanToDatabase(@RequestBody AddChangePlanRequest request){
		
		Optional<ChangePlan> plan = commonCarService.saveChangePlan(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(plan);
		
	}
	
	@GetMapping("/getChangePlan")
	public ResponseEntity<DisplayChangePlanFinalResponse> displayChangePlan(){
		
		DisplayChangePlanFinalResponse response = commonCarService.displayChangePlan();
		if(response.getResponse()!=null && !response.getResponse().isEmpty()) {
			return ResponseEntity.ok(response);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DisplayChangePlanFinalResponse());
		}
	}
	
	@PostMapping("/addGoldDetails")
	public ResponseEntity<Optional<GoldCheck>> addGoldToDatabase(@RequestBody AddGoldCheckRequest request){
		
		Optional<GoldCheck> checks = commonCarService.addGoldToDatabase(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(checks);
		
	}
	
	
	@PostMapping("/checkGold")
	public List<DisplayGoldCheckResponse> displayGoldRate(@RequestBody CheckGoldRequest request){
		return commonCarService.displayGoldRate(request);
	}
	
	@PostMapping("/lightrate")
	public ResponseEntity<Optional<LightRate>> saveLightRate(@RequestBody AddLightRateRequest request){
		
		Optional<LightRate> rate = commonCarService.addLightrate(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(rate);
	}
	
	@PostMapping("/saveLightBillData")
	public ResponseEntity<Optional<LightBill>> saveLightBillData(@RequestBody AddLightBillRequest request){
		
		Optional<LightBill> bill = commonCarService.saveLightBill(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(bill);
	}
	
	
	@PostMapping("/getLightBill")
	public ResponseEntity<List<DisplayElectricityBillResponse>> displayTheLightBill(@RequestBody CheckBillRequest request){
		
		List<DisplayElectricityBillResponse> response = commonCarService.displayElectricityBill(request);
		if(response!=null && !response.isEmpty()) {
			return ResponseEntity.ok(response);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<DisplayElectricityBillResponse>());
		}
	}
}
