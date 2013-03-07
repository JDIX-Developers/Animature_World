package com.jdix.animature.entities;

import android.graphics.drawable.Drawable;

public class Animature 
{
	private String name;
	private double height;
	private double weight;
	private String type;
	private int [] cualities;			// 0-Velocity, 1-Defense, 2-Agility, 3-Strenght
	private int health;
	private int level_evo;
	private Drawable [] imgAnimature;	// 0-FrontImage, 1-BackImage
	private Drawable iconAnimature;
	
	public Animature()
	{
		
	}
	public Animature(String name, double height, double weight,
					String type, int [] cualities, int health,
					int level_evo, Drawable [] imgAnimature,
					Drawable iconAnimature){
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.type = type;
		this.cualities = cualities;
		this.health = health;
		this.level_evo = level_evo;
		this.imgAnimature = imgAnimature;
		this.iconAnimature = iconAnimature;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int[] getCualities() {
		return cualities;
	}
	public void setCualities(int[] cualities) {
		this.cualities = cualities;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getLevel_evo() {
		return level_evo;
	}
	public void setLevel_evo(int level_evo) {
		this.level_evo = level_evo;
	}
	public Drawable[] getImgAnimature() {
		return imgAnimature;
	}
	public void setImgAnimature(Drawable[] imgAnimature) {
		this.imgAnimature = imgAnimature;
	}
	public Drawable getIconAnimature() {
		return iconAnimature;
	}
	public void setIconAnimature(Drawable iconAnimature) {
		this.iconAnimature = iconAnimature;
	}
}
