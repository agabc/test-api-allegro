package pl.allegro.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

public class Options {

    @SerializedName("advertisement")
    @Expose
    private Boolean advertisement;
    @SerializedName("advertisementPriceOptional")
    @Expose
    private Boolean advertisementPriceOptional;
    @SerializedName("variantsByColorPatternAllowed")
    @Expose
    private Boolean variantsByColorPatternAllowed;
    @SerializedName("offersWithProductPublicationEnabled")
    @Expose
    private Boolean offersWithProductPublicationEnabled;
    @SerializedName("productCreationEnabled")
    @Expose
    private Boolean productCreationEnabled;
    @SerializedName("customParametersEnabled")
    @Expose
    private Boolean customParametersEnabled;

    public Boolean getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Boolean advertisement) {
        this.advertisement = advertisement;
    }

    public Boolean getAdvertisementPriceOptional() {
        return advertisementPriceOptional;
    }

    public void setAdvertisementPriceOptional(Boolean advertisementPriceOptional) {
        this.advertisementPriceOptional = advertisementPriceOptional;
    }

    public Boolean getVariantsByColorPatternAllowed() {
        return variantsByColorPatternAllowed;
    }

    public void setVariantsByColorPatternAllowed(Boolean variantsByColorPatternAllowed) {
        this.variantsByColorPatternAllowed = variantsByColorPatternAllowed;
    }

    public Boolean getOffersWithProductPublicationEnabled() {
        return offersWithProductPublicationEnabled;
    }

    public void setOffersWithProductPublicationEnabled(Boolean offersWithProductPublicationEnabled) {
        this.offersWithProductPublicationEnabled = offersWithProductPublicationEnabled;
    }

    public Boolean getProductCreationEnabled() {
        return productCreationEnabled;
    }

    public void setProductCreationEnabled(Boolean productCreationEnabled) {
        this.productCreationEnabled = productCreationEnabled;
    }

    public Boolean getCustomParametersEnabled() {
        return customParametersEnabled;
    }

    public void setCustomParametersEnabled(Boolean customParametersEnabled) {
        this.customParametersEnabled = customParametersEnabled;
    }

}