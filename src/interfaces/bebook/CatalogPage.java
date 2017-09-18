package bebook;

import org.openqa.selenium.By;

public class CatalogPage {

	public static final By btnNewCatalog = By.id("catalog_create");
	public static final By btnValidate = By.cssSelector("#dialog_type button");
	public static final By divCatalogName = By.className("catalog_name");
	public static final By txtCatalogName = By.cssSelector(".catalog_name .inplace_field");
	public static final By txtCatalogTitle = By.id("catalog_title");
	public static final By btnEditCatalog = By.id("catalog_action_Edit");
	public static final By btnDeleteCatalog = By.id("catalog_delete");
	public static final By btnNewCategory = By.id("catalog_create_category");
	public static final By spnFirstCategory = By.cssSelector("#catalog_tree .col_PO");
	public static final By txtCategoryName = By.cssSelector(".inplace_form input");
	public static final By btnNewProduct = By.id("catalog_create_product");
	public static final By spnFirstProduct = By.cssSelector("#catalog_tree .product .col_PO");
	public static final By txtProductName = By.cssSelector(".inplace_form input");
	public static final By btnAccptDelete = By.xpath("//div[@id='dialog_delete']/following-sibling::div//button[text()='Yes']");
	public static final By ifmCatalog = By.cssSelector("iframe#catalog_frame");
	
	public static final String lblDynamicCatalogName = "//div[@class='catalog_name'][text()='%s']";
	public static final String lblDynamicCategoryName = "//div[@id='catalog_tree']//span[contains(@class, 'col_PO')][text()='%s']";
	public static final String lblDynamicProductName = "//div[@id='catalog_tree']//li[contains(@class, 'product')]//span[contains(@class, 'col_PO')][text()='%s']";
}
