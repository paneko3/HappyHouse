package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AnswerDto;
import com.ssafy.happyhouse.model.dto.DongDto;
import com.ssafy.happyhouse.model.dto.FacilityDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dto.StationDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;
import com.ssafy.happyhouse.model.service.HouseMapService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	@Autowired
	private HouseMapMapper houseMapMapper;

	@ApiOperation(value = "전국 시도정보를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "시도코드에 해당하는 구군정보를 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@ApiOperation(value = "구군코드에 해당하는 동정보를 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<DongDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<DongDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@ApiOperation(value = "동코드에 해당하는 아파트정보를 반환한다.", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트코드에 해당하는 거래정보를 반환한다.", response = List.class)
	@GetMapping("/deal")
	public ResponseEntity<List<HouseDealDto>> deal(@RequestParam("aptCode") String aptCode) throws Exception {
		return new ResponseEntity<List<HouseDealDto>>(haHouseMapService.getAptDeal(aptCode), HttpStatus.OK);
	}
	
	
//	@ApiOperation(value = "위치 기준 시설을 반환한다.", response = List.class)
//	@GetMapping("/aptfacility")
//	public ResponseEntity<List<FacilityDto>> facility(@RequestParam("lat") String lat,@RequestParam("lng") String lng,@RequestParam(value="zoomlevel",defaultValue = "5") String zoomlevel,
//			 @RequestParam(value="category",required = false) String category) throws Exception {
//		return new ResponseEntity<List<FacilityDto>>(haHouseMapService.getFacility(lat,lng,zoomlevel,category), HttpStatus.OK);
//	}
	
//	@ApiOperation(value = "위치 기준 지하철 반환한다.", response = List.class)
//	@GetMapping("/station")
//	public ResponseEntity<List<StationDto>> station(@RequestParam("lat") String lat,@RequestParam("lng") String lng,@RequestParam(value="zoomlevel",defaultValue = "5")String zoomlevel)throws Exception {
//		return new ResponseEntity<List<StationDto>>(haHouseMapService.getStation(lat,lng,zoomlevel), HttpStatus.OK);
//	}
	
	@ApiOperation(value = "좌표 기준 주변 아파트를 반환한다.", response = List.class)
	@GetMapping("/currentlocation")
	public ResponseEntity<List<HouseInfoDto>> currentlocation(@RequestParam("lat") String lat,@RequestParam("lng") String lng,@RequestParam(value="zoomlevel",defaultValue = "5") String zoomlevel,
			@RequestParam(value="pricemax",required = false) String pricemax, @RequestParam(value="pricemin",required = false) String pricemin,
			@RequestParam(value="areamax",required = false) String areamax, @RequestParam(value="areamin",required = false) String areamin) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptAround(lat,lng,zoomlevel,pricemax,pricemin,areamax,areamin), HttpStatus.OK);
	}
	
	@ApiOperation(value = "좌표 기준 주변 시설를 반환한다.", response = Map.class)
	@GetMapping("/currentfacility")
	public ResponseEntity<Map<String, List<FacilityDto>>> currentfacility(@RequestParam("lat") String lat,@RequestParam("lng") String lng, @RequestParam(value="zoomlevel",defaultValue = "5") String zoomlevel) throws Exception {
		Map<String,List<FacilityDto>> maps = new HashMap<String, List<FacilityDto>>();
		List<FacilityDto> list = new ArrayList<FacilityDto>();
		list = houseMapMapper.getFacility(lat,lng, zoomlevel, "스타벅스");
		maps.put("starbucks", list);
		list = new ArrayList<FacilityDto>();
		list = houseMapMapper.getFacility(lat,lng, zoomlevel, "맥도날드");
		maps.put("mac", list);
		list = new ArrayList<FacilityDto>();
		list = houseMapMapper.getFacility(lat,lng, zoomlevel, "다이소");
		maps.put("daiso", list);
		list = new ArrayList<FacilityDto>();
		list = houseMapMapper.getFacility(lat,lng, zoomlevel, "영화관");
		maps.put("cinema", list);
		list = new ArrayList<FacilityDto>();
		list = houseMapMapper.getFacility(lat,lng, zoomlevel, "편의점");
		maps.put("store", list);
		return new ResponseEntity<Map<String, List<FacilityDto>>>(maps, HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트코드에 해당하는 가장 가까운 시설의 거리 반환", response = List.class)
	@GetMapping("/nearfacilitydis")
	public ResponseEntity<Map<String,String>> nearfacilitydis(@RequestParam("aptCode") String aptCode) throws Exception {
		Map<String,String> maps =  new HashMap<String, String>();
		String str = new String();
		str = houseMapMapper.getNearStationDis(aptCode,"1");
		maps.put("station", str);
		str = new String();
		str = houseMapMapper.getAptFacilityDis(aptCode,"1","맥도날드");
		maps.put("mac", str);
		str = new String();
		str = houseMapMapper.getAptFacilityDis(aptCode,"1","다이소");
		maps.put("daiso", str);
		str = houseMapMapper.getAptFacilityDis(aptCode,"3","영화관");
		maps.put("cinema", str);
		str = new String();
		str = houseMapMapper.getAptFacilityDis(aptCode,"1","편의점");
		maps.put("store", str);
		str = new String();
		str = houseMapMapper.getAptFacilityDis(aptCode,"1","스타벅스");
		maps.put("starbucks", str);
		return new ResponseEntity<Map<String, String>>(maps, HttpStatus.OK);
	}

}
