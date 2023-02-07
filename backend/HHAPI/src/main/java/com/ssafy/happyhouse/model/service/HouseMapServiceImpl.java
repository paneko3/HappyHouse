package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.DongDto;
import com.ssafy.happyhouse.model.dto.FacilityDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dto.StationDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<DongDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}
	public List<HouseDealDto> getAptDeal(String aptCode) throws Exception{
		return houseMapMapper.getAptDeal(aptCode);
	}
	
	
	public List<FacilityDto> getFacility(String lat,String lng,String zoomlevel,String category) throws Exception{
		return houseMapMapper.getFacility(lat,lng,zoomlevel,category);
	}
	
	public List<StationDto> getStation(String lat,String lng,String zoomlevel) throws Exception{
		return houseMapMapper.getStation(lat,lng,zoomlevel);
	}
	
	public List<HouseInfoDto> getAptAround(String lat,String lng,String zoomlevel,String pricemax, String pricemin,String areamax, String areamin) throws Exception{
		return houseMapMapper.getAptAround(lat,lng,zoomlevel, pricemax, pricemin, areamax, areamin);
	}
	
	public String getAptFacilityDis(String aptCode,String dis, String category)throws Exception{
		return houseMapMapper.getAptFacilityDis(aptCode, dis, category);
	}
	public String getNearStationDis(String aptCode,String dis)throws Exception{
		return houseMapMapper.getNearStationDis(aptCode, dis);
	}

}
