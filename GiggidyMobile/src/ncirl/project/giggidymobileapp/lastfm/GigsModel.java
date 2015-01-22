package ncirl.project.giggidymobileapp.lastfm;

import java.util.ArrayList;

import android.text.TextUtils;

/**
 * @author paddybyrne
 * 
 */
public class GigsModel {

	// From root JSON element
	private String gigId;
	private String gigDate;
	private String artistImgURL;
	private String gigVenue;
	private ArrayList<String> tags;
	private String artistSummary;

	//From the venue JSON element
	private String geoLat;
	private String geoLong;
	private String venueNumber;
	private String venueImgURL;

	// From artists array element
	private String gigHeadliner;
	private ArrayList<String> allArtists;

	public String getGeoLat() {
		return geoLat;
	}

	public void setGeoLat(String geoLat) {
		this.geoLat = geoLat;
	}

	public String getGeoLong() {
		return geoLong;
	}

	public void setGeoLong(String geoLong) {
		this.geoLong = geoLong;
	}

	public String getVenueNumber() {
		return venueNumber;
	}

	public void setVenueNumber(String venueNumber) {
		this.venueNumber = venueNumber;
	}

	public void setGigDate(String gigDate) {
		this.gigDate = gigDate;
	}

	public String getGigId() {
		return gigId;
	}

	public String getGigVenue() {
		return gigVenue;
	}

	public void setGigVenue(String gigVenue) {
		this.gigVenue = gigVenue;

	}

	public String getGigDate() {
		return gigDate;
	}

	public void setGigId(String gigId) {
		this.gigId = gigId;
	}

	public String getArtistImgURL() {
		return artistImgURL;
	}

	public void setArtistImgURL(String artistImgURL) {
		this.artistImgURL = artistImgURL;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getGigHeadliner() {
		return gigHeadliner;
	}

	public void setGigHeadliner(String gigHeadliner) {
		this.gigHeadliner = gigHeadliner;
	}

	public String getAllArtists() {
		return TextUtils.join(", ", allArtists);
	}

	public void setAllArtists(ArrayList<String> allArtists) {
		this.allArtists = allArtists;
	}

	public String getVenueImgURL() {
		return venueImgURL;
	}

	public void setVenueImgURL(String venueImgURL) {
		this.venueImgURL = venueImgURL;
	}

	
	
	

}