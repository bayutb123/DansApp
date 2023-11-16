package com.bayutb123.dansapp.data.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PositionsResponse{

	@SerializedName("PositionsResponse")
	private List<PositionsResponseItem> positionsResponse;

	public List<PositionsResponseItem> getPositionsResponse(){
		return positionsResponse;
	}
}