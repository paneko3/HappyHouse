package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.DongDto;
import com.ssafy.happyhouse.model.dto.FacilityDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dto.StationDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<DongDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseDealDto> getAptDeal(String aptCode) throws Exception;
	
	List<FacilityDto> getFacility(String lat,String lng,String zoomlevel,String category) throws Exception;
	List<StationDto> getStation(String lat,String lng,String zoomlevel) throws Exception;
	
	List<HouseInfoDto> getAptAround(String lat,String lng,String zoomlevel,String pricemax, String pricemin,String areamax, String areamin) throws Exception; 
	
//	String getNearFacilityDis(String lat,String lng,String dis, String category)throws Exception;
	String getAptFacilityDis(String aptCode,String dis, String category)throws Exception;
	String getNearStationDis(String aptCode,String dis)throws Exception;
	
	
}
