package edu.calpoly.csc409.crave.pojos;

public class NutritionFacts {
	protected double mProtein;
	protected double mTotalFat;
	protected double mCarbs;
	protected double mCalories;
	protected double mAlcohol;
	protected double mWater;
	protected double mCaffeine;
	protected double mTheobromine;
	protected double mSugar;
	protected double mFiber;
	protected double mCalcium;
	protected double mIron;
	protected double mMagnesium;
	protected double mPhosphorus;
	protected double mPotassium;
	protected double mSodium;
	protected double mZinc;
	protected double mCopper;
	protected double mSelenium;
	protected double mRetinol;
	protected double mVitA;
	protected double mCaroteneB;
	protected double mCaroteneA;
	protected double mVitE;
	protected double mVitD;
	protected double mCryptoxanthin;
	protected double mLycopene;
	protected double mLutein;
	protected double mVitC;
	protected double mThiamin;
	protected double mRiboflavin;
	protected double mNiacin;
	protected double mVitB6;
	protected double mVitB12;
	protected double mCholine;
	protected double mVitK;
	protected double mFolicAcid;
	protected double mFolateFood;
	protected double mFolateDFE;
	protected double mVitEAdded;
	protected double mVitBAdded;
	protected double mCholesterol;
	protected double mSatFat;
    protected String mServSize;

	public String getProteinString() {
		return mProtein + "g";
	}

	public void setProtein(double mProtein) {
		this.mProtein = mProtein;
	}

	public String getTotalFatString() {
		return mTotalFat + "g";
	}

	public void setTotalFat(double mTotalFat) {
		this.mTotalFat = mTotalFat;
	}

	public String getCarbsString() {
		return mCarbs + "g";
	}

	public void setCarbs(double mCarbs) {
		this.mCarbs = mCarbs;
	}

	public String getCaloriesString() {
		return Double.toString(mCalories);
	}

	public void setCalories(double mCalories) {
		this.mCalories = mCalories;
	}

	public String getAlcoholString() {
		return mAlcohol + "g";
	}

	public void setAlcohol(double mAlcohol) {
		this.mAlcohol = mAlcohol;
	}

	public String getWaterString() {
		return mWater + "g";
	}

	public void setWater(double mWater) {
		this.mWater = mWater;
	}

	public String getCaffeineString() {
		return mCaffeine + "mg";
	}

	public void setCaffeine(double mCaffeine) {
		this.mCaffeine = mCaffeine;
	}

	public String getTheobromineString() {
		return mTheobromine + "mg";
	}

	public void setTheobromine(double mTheobromine) {
		this.mTheobromine = mTheobromine;
	}

	public String getSugarString() {
		return mSugar + "g";
	}

	public void setSugar(double mSugar) {
		this.mSugar = mSugar;
	}

	public String getFiberString() {
		return mFiber + "g";
	}

	public void setFiber(double mFiber) {
		this.mFiber = mFiber;
	}

	public String getCalciumString() {
		return mCalcium + "mg";
	}

	public void setCalcium(double mCalcium) {
		this.mCalcium = mCalcium;
	}

	public String getIronString() {
		return mIron + "mg";
	}

	public void setIron(double mIron) {
		this.mIron = mIron;
	}

	public String getMagnesiumString() {
		return mMagnesium + "mg";
	}

	public void setMagnesium(double mMagnesium) {
		this.mMagnesium = mMagnesium;
	}

	public String getPhosphorusString() {
		return mPhosphorus + "mg";
	}

	public void setPhosphorus(double mPhosphorus) {
		this.mPhosphorus = mPhosphorus;
	}

	public String getPotassiumString() {
		return mPotassium + "mg";
	}

	public void setPotassium(double mPotassium) {
		this.mPotassium = mPotassium;
	}

	public String getSodiumString() {
		return mSodium + "mg";
	}

	public void setSodium(double mSodium) {
		this.mSodium = mSodium;
	}

	public String getZincString() {
		return mZinc + "mg";
	}

	public void setZinc(double mZinc) {
		this.mZinc = mZinc;
	}

	public String getCopperString() {
		return mCopper + "mg";
	}

	public void setCopper(double mCopper) {
		this.mCopper = mCopper;
	}

	public String getSeleniumString() {
		return mSelenium + "mcg";
	}

	public void setSelenium(double mSelenium) {
		this.mSelenium = mSelenium;
	}

	public String getRetinolString() {
		return mRetinol + "mcg";
	}

	public void setRetinol(double mRetinol) {
		this.mRetinol = mRetinol;
	}

	public String getVitAString() {
		return mVitA + "mcg";
	}

	public void setVitA(double mVitA) {
		this.mVitA = mVitA;
	}

	public String getCaroteneBString() {
		return mCaroteneB + "mcg";
	}

	public void setCaroteneB(double mCaroteneB) {
		this.mCaroteneB = mCaroteneB;
	}

	public String getCaroteneAString() {
		return mCaroteneA + "mcg";
	}

	public void setCaroteneA(double mCaroteneA) {
		this.mCaroteneA = mCaroteneA;
	}

	public String getVitEString() {
		return mVitE + "mg";
	}

	public void setVitE(double mVitE) {
		this.mVitE = mVitE;
	}

	public String getVitDString() {
		return mVitD + "mcg";
	}

	public void setVitD(double mVitD) {
		this.mVitD = mVitD;
	}

	public String getCryptoxanthinString() {
		return mCryptoxanthin + "mcg";
	}

	public void setCryptoxanthin(double mCryptoxanthin) {
		this.mCryptoxanthin = mCryptoxanthin;
	}

	public String getLycopeneString() {
		return mLycopene + "mcg";
	}

	public void setLycopene(double mLycopene) {
		this.mLycopene = mLycopene;
	}

	public String getLuteinString() {
		return mLutein + "mcg";
	}

	public void setLutein(double mLutein) {
		this.mLutein = mLutein;
	}

	public String getVitCString() {
		return mVitC + "mg";
	}

	public void setVitC(double mVitC) {
		this.mVitC = mVitC;
	}

	public String getThiaminString() {
		return mThiamin + "mg";
	}

	public void setThiamin(double mThiamin) {
		this.mThiamin = mThiamin;
	}

	public String getRiboflavinString() {
		return mRiboflavin + "mg";
	}

	public void setRiboflavin(double mRiboflavin) {
		this.mRiboflavin = mRiboflavin;
	}

	public String getNiacinString() {
		return mNiacin + "mg";
	}

	public void setNiacin(double mNiacin) {
		this.mNiacin = mNiacin;
	}

	public String getVitB6String() {
		return mVitB6 + "mg";
	}

	public void setVitB6(double mVitB) {
		this.mVitB6 = mVitB;
	}
	
	public String getVitB12String() {
		return mVitB12 + "mcg";
	}

	public void setVitB12(double mVitB) {
		this.mVitB12 = mVitB;
	}

	public String getCholineString() {
		return mCholine + "mg";
	}

	public void setCholine(double mCholine) {
		this.mCholine = mCholine;
	}

	public String getVitKString() {
		return mVitK + "mcg";
	}

	public void setVitK(double mVitK) {
		this.mVitK = mVitK;
	}

	public String getFolicAcidString() {
		return mFolicAcid + "mcg";
	}

	public void setFolicAcid(double mFolicAcid) {
		this.mFolicAcid = mFolicAcid;
	}

	public String getFolateFoodString() {
		return mFolateFood + "mcg";
	}

	public void setFolateFood(double mFolateFood) {
		this.mFolateFood = mFolateFood;
	}

	public String getFolateDFEString() {
		return mFolateDFE + "mcg";
	}

	public void setFolateDFE(double mFolateDFE) {
		this.mFolateDFE = mFolateDFE;
	}

	public String getVitEAddedString() {
		return mVitEAdded + "mg";
	}

	public void setVitEAdded(double mVitEAdded) {
		this.mVitEAdded = mVitEAdded;
	}

	public String getVitBAddedString() {
		return mVitBAdded + "mcg";
	}

	public void setVitBAdded(double mVitBAdded) {
		this.mVitBAdded = mVitBAdded;
	}

	public String getCholesterolString() {
		return mCholesterol + "mg";
	}

	public void setCholesterol(double mCholesterol) {
		this.mCholesterol = mCholesterol;
	}

	public String getSatFatString() {
		return mSatFat + "g";
	}

	public void setSatFat(double mSatFat) {
		this.mSatFat = mSatFat;
	}

    public String getServSize() {
        return mServSize;
    }

    public void setServSize(String servSize) {
        this.mServSize = servSize;
    }
}

