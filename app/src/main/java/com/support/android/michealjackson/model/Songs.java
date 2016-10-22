package com.support.android.michealjackson.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Songs  implements Parcelable {

	public String artistName;
	public String collectionName;
	public String trackName;
	public String artworkUrl100;
	public String trackViewUrl;
	public String previewUrl;
	public String releaseDate;
	public String primaryGenreName;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(artistName);
		dest.writeString(collectionName);
		dest.writeString(trackName);
		dest.writeString(artworkUrl100);
		dest.writeString(trackViewUrl);
		dest.writeString(previewUrl);
		dest.writeString(releaseDate);
		dest.writeString(primaryGenreName);
		
	}

	// Creator
	public static final Parcelable.Creator<Songs> CREATOR
			= new Creator<Songs>() {
		public Songs createFromParcel(Parcel in) {
			Songs s = new Songs();
			s.artistName = in.readString();
			s.collectionName= in.readString();
			s.trackName= in.readString();
			s.artworkUrl100= in.readString();
			s.trackViewUrl= in.readString();
			s.previewUrl= in.readString();
			s.releaseDate= in.readString();
			s.primaryGenreName= in.readString();

			return s;
		}

		public Songs[] newArray(int size) {
			return new Songs[size];
		}
	};

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getArtworkUrl100() {
		return artworkUrl100;
	}

	public void setArtworkUrl100(String artworkUrl100) {
		this.artworkUrl100 = artworkUrl100;
	}

	public String getTrackViewUrl() {
		return trackViewUrl;
	}

	public void setTrackViewUrl(String trackViewUrl) {
		this.trackViewUrl = trackViewUrl;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPrimaryGenreName() {
		return primaryGenreName;
	}

	public void setPrimaryGenreName(String primaryGenreName) {
		this.primaryGenreName = primaryGenreName;
	}

}
