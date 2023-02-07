package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.DongDto;
import com.ssafy.happyhouse.model.dto.FacilityDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dto.StationDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<DongDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseDealDto> getAptDeal(String aptCode) throws SQLException;
	
	
	List<FacilityDto> getFacility(String lat,String lng,String zoomlevel,String category) throws SQLException;
	List<StationDto> getStation(String lat,String lng,String zoomlevel) throws SQLException;
	
	List<HouseInfoDto> getAptAround(String lat,String lng,String zoomlevel,String pricemax, String pricemin,String areamax, String areamin) throws SQLException;
	
	String getAptFacilityDis(String aptCode,String dis, String category)throws SQLException;
	String getNearStationDis(String aptCode,String dis)throws SQLException;
}
