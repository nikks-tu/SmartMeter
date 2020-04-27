package com.techuva.smartmeter.constants;

/**
 * Created by nikita on 4/16/2019.
 */
public interface Constants {
    //Base Url for Login with Token
    String BASE_URL_TOKEN = "http://182.18.177.27:8687/TSM/";
    //For Dev2
    //String BASE_URL_TOKEN = "http://182.18.177.27:9555/TSM/";
    //For Prod
    //String BASE_URL_TOKEN = "https://smartenergysol.in:8443/apis/TSM/";
    String CurrentData = "Service/currentdata";
    String LoginData = "login";
    String ForgetPassword = "forgotPassword";
    String ChangePassword = "changePassword";
    String ListofConnections = "ConsumerDashboard/getConnectionsList";
    String HistoryData = "inventoryHistoricalData";
    String GetRechargeHistoryDetails = "Recharge/getRechargeHistoreyDetails";
    String VersionCheck = "Service/VersionCheckInfo";
    String AccountList = "ConsumerDashboard/getConnectionsList";
    String RechargeHistory = "Recharge/getRechargeHistoreyDetails";
    String RechargeCalculation ="Recharge/calculate";
    String RechargeInsert ="Recharge/insert";
    String GetConsumedData = "Service/getconsumeddetails";
    String GetConsumedDataList = "EnergyConsumptionHistory/getEnergyConsumptionHistory";
    //Payment Gateway
    String RechargeUpdate ="Recharge/update";
    String GetRechargePostpaidBill = "Recharge/PostPaid/getBillData";
    String GetPrepaidBillDetails = "Recharge/getRechargeDetails";
    String GetPostpaidBillDetails = "Recharge/getPostPaidBillDetailsData";
    String PostpaidBillInsert = "Recharge/PostPaid/insert";
    String PostpaidRechargeHistory = "Recharge/getPostPaidBillDetails";
    String GetDenomination ="DenominationMaster/getDropdown";
    String ScratchCardCalculation = "Recharge/Scratch/calculate";
    String ScratchCardInsert = "Recharge/Scratch/insert";
    String LifeSaverRechargeCalculation = "Recharge/LifeSaver/calculate";
    String LifeSaverRechargeInsert = "Recharge/LifeSaver/insert";
    String PrepaidRechargeUpdate = "Recharge/update";
    String PostpaidRechargeUpdate = "Recharge/PostPaid/update";
    String PrepaidInvoiceDownload = "Recharge/preRechargeInvoice";
    String GetPowerStatus = "Recharge/preRechargeInvoice";
    String GetLifesaverDropDown = "LifeSaver/getLifeSaverDropDown";
    String GetMeterInstallmentDetails = "ConsumerDashboard/getMeterInstallmentOfConsumer";
    String GetMeterMigrationDetails = "MeterApplication/GetDeviceMigrationDetails";
    String MigrationScheduleCalculate = "Recharge/migrationschedule/calculate";
    String MeterLoanCalculate = "Recharge/meterInstall/calculate";
    String MigrationScheduleInsert = "Recharge/migrationschedule/insert";
    String MeterLoanInsert = "Recharge/meterInstall/insert";
    String MigrationScheduleUpdateRecharge= "Recharge/migrationschedule/update";
    String MeterLoanUpdateRecharge= "Recharge/meterInstall/update";

    int SelectedChannel = 1;
    //Test URL
    String USER_MGMT_URL = "http://182.18.177.27/TUUserManagement/api/user/";
    String Ref_Type = "MOBILE";
    String DeviceID = "DeviceID";
    String InventoryName = "InventoryName";
    String CompanyID ="CompanyID";
    String UserID ="UserID";
    String UserName ="UserName";
    String UserMailId ="UserMailId";
    String AppVersion = "1";
    String FontVersion = "0";
    String InventoryTypeId = "InventoryTypeId";

    String SingleAccount="SingleAccount";

    String IsLoggedIn = "IsLoggedIn";
    String IsHomeSelected = "IsHomeSelected";
    String IsDefaultDeviceSaved = "IsDefaultDeviceSaved";
    String Template = "Template";
    String ChannelNumGraph = "ChannelNumGraph";
    String IsUSNSelected ="IsUSNSelected";
    String SelectedUSN = "SelectedUSN";

    public static final String NULL_STRING = "null";
    public static String DEVICE_IN_USE = "";
    public static String COMPANY_ID = "COMPANY_ID";

    //90 days token
    String AuthorizationKey = "Basic dGVjaHV2YS1jbGllbnQtbW9iaWxlOnNlY3JldA==";
    //1 hr token
    //String AuthorizationKey = "Basic dGVjaHV2YS1jbGllbnQ6c2VjcmV0";
    String EncryptionKey ="7290608a5fcbd5e3952d8046";
    String GrantType= "password";
    String GrantTypeRefresh= "refresh_token";
    String AccessToken ="AccessToken";
    String RefreshToken ="RefreshToken";
    String SecondsToExpireToken ="SecondsToExpireToken";
    String DateToExpireToken ="DateToExpireToken";
    String IsSessionExpired ="IsSessionExpired";
    //Device Info
    String INVENTORY_ID = "INVENTORY_ID";
    String USN_NUM = "USN_NUM";
    String BALANCE = "BALANCE";
    String CONNECTION_TYPE = "CONNECTION_TYPE";
    String TARIFF = "TARIFF";
    String ZONE = "ZONE";
    String CURRENCY = "CURRENCY";
    String LIFE_SAVER = "LIFE_SAVER";
    String LIFE_SAVER_DETAILS = "LIFE_SAVER_DETAILS";
    String FROM_LIFESAVER = "FROM_LIFESAVER";
    String POSTPAID_BILLING_ID  = "postpaidBillingId";
    /*Recharge related constants*/
    String RECHARGE_STATUS = "RECHARGE_STATUS";
    String RECHARGE_AMOUNT = "RECHARGE_AMOUNT";
    String CURRENCY_OF_RECHARGE = "CURRENCY_OF_RECHARGE";
    String PAYMENT_REF_NUM = "PAYMENT_REF_NUM";
    String DATE_OF_RECHARGE = "DATE_OF_RECHARGE";
    String PAYMENT_METHOD = "PAYMENT_METHOD";
    String PREPAID_RECHARGE_ID = "PREPAID_RECHARGE_ID";
    String From_Card = "From_Card";
    String Actual_Recharge_Amount = "Actual_Recharge_Amount";
    /*Connection Types*/
    String Postpaid_Connection= "Postpaid_Connection";
    String Prepaid_Connection="Prepaid_Connection";
    String From_Bill_History="From_Bill_History";
    String IsFromRechargeCard="IsFromRechargeCard";
    String Phase_Type="Phase_Type";
    String District_Name = "District_Name";
    String MGR_PAYMENT_ID = "MGR_PAYMENT_ID";
    String LOAN_EMI_ID = "LOAN_EMI_ID";
    String MIGRATION = "MIGRATION";
    String METER_LOAN = "METER_LOAN";
    String INSTALLMENT_TYPE = "INSTALLMENT_TYPE";
}
