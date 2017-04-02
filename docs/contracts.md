# Contracts

This guide walks you through retrieving current user's accounts contracts. Four contract groups are available:

- Buildings
- Insurances
- Loyalty
- Pensions

[ContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/ContractsResource.java)

```java

    // Get ContractsResource Instance
    Netbanking.getInstance().getNetbankingClient().getContractsResource()...

```
## A. Buildings

### 1. List all building contracts
You can list all of current users building contracts by calling the `list` method on [BuildingsContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/buildings/BuildingsContractsResource.java). 

```java

    // List all building contracts
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getBuildingsResource().
        .list(PaginatedParameters parameters, CallbackWebApi<BuildingsListResponse> callback);

```

### 2. Get individual current users building contract

You can get detail of the individual current users building contract by calling the `withId` method on [BuildingsContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/buildings/BuildingsContractsResource.java) with id as a parameter and then calling the get method. For complete response see [BuildingsContract](../netbanking/src/main/java/cz/csas/netbanking/contracts/buildings/BuildingsContract.java).

```java

    // Building contract detail
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getBuildingsResource().
        .withId(Object id)
        .get(CallbackWebApi<BuildingsContract> callback);

```

### 3. Update current users building contract

You can update current users building contract by calling the `withId` method on [BuildingsContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/buildings/BuildingsContractsResource.java) with id as a parameter and then calling the update method and giving it payload in object as a parameter. For payload properties please see [BuildingsContractUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/contracts/buildings/BuildingsContractUpdateRequest.java) and check also the response [BuildingsContractUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/contracts/buildings/BuildingsContractUpdateResponse.java).

```java

    // Building contract update
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getBuildingsResource().
        .withId(Object id)
        .update(BuildingsContractUpdateRequest request, CallbackWebApi<BuildingsContractUpdateResponse> callback);

```

### 4. List building contracts services

List building contract services by getting the [BuildingsContractsServicesResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/buildings/BuildingsContractsServicesResource.java) and then calling the `list` method. The method takes object with properties as a parameter. Services resource supports pagination.

```java

    // Building contract services
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getBuildingsResource().
            .withId(Object id)
            .getServicesResource()
            .list(PaginatedParameters parameters, CallbackWebApi<ServicesListResponse> callback);

```

### 5. Add or change note and mark buildings transaction

Add, change or mark transaction by calling the `update` method on [CzTransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/CzTransactionsResource.java) and passing it object with options as a parameter. For payload properties please see [TransactionUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateRequest.java) and check also the response [TransactionUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateResponse.java).

```java

    // Building contract transaction update
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getBuildingsResource().
            .withId(Object id)
            .getTransactionsResource()
            .withId(Object id)
            .update(TransactionUpdateRequest request, CallbackWebApi<TransactionUpdateResponse> callback);

```

### 6. Export buildings transactions history

Export transaction history into signed PDF by calling the `export` method on [CzTransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/CzTransactionsResource.java) and passing it object with options as a parameter. See [ExportTransactionsParameters](../netbanking/src/main/java/cz/csas/netbanking/ExportTransactionsParameters.java).

```java

    // Export building contract transactions history
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getBuildingsResource().
            .withId(Object id)
            .getTransactionsResource()
            .getExportResource()
            .export(ExportTransactionsParameters parameters, CallbackWebApi<WebApiStream> callback) ;

```

## B. Insurances

### 1. List all insurance contracts
You can list all of current users insurance contracts by calling the `list` method on [InsurancesContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractsResource.java). 

```java

    // List all insurance contracts
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsurancesResource().
        .list(PaginatedParameters parameters, CallbackWebApi<InsuranceListResponse> callback);

```

### 2. Get individual current users insurance contract

You can get detail of the individual current users insurance contract by calling the `withId` method on [InsurancesContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractsResource.java) with id as a parameter and then calling the get method. For complete response see [Insurance](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/Insurance.java).

```java

    // Insurance contract detail
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsurancesResource().
        .withId(Object id)
        .get(CallbackWebApi<Insurance> callback);

```

### 3. Update current users insurance contract

You can update current users insurance contract by calling the `withId` method on [InsurancesContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractsResource.java) with id as a parameter and then calling the update method and giving it payload in object as a parameter. For payload properties please see [InsuranceUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsuranceUpdateRequest.java) and check also the response [InsuranceUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsuranceUpdateResponse.java).

```java

    // Insurance contract update
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
        .withId(Object id)
        .update(InsuranceUpdateRequest request, CallbackWebApi<InsuranceUpdateResponse> callback);

```

### 4. Get insurance contract detail

Get insurance contract detail by getting the [InsurancesContractDetailResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractDetailResource.java) and then calling the `get` method. For complete response see [InsuranceDetail](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsuranceDetail.java).

```java

    // Insurace contract detail
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getDetailResource()
            .get(CallbackWebApi<InsuranceDetail> callback);

```

### 5. List insurance contract funds

You can list all of current users insurance contract funds by calling the `list` method on [InsurancesContractFundsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractFundsResource.java). 

```java

    // Insurance contract funds list
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getFundsResource()
            .list(CallbackWebApi<FundsListResponse> callback);

```

### 6. Update insurance contract funds

You can update current users insurance contract funds by calling the `update` method on [InsurancesContractFundsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractFundsResource.java) and giving it payload in object as a parameter. For payload properties please see [FundsUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/FundsUpdateRequest.java) and check also the response [FundsUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/FundsUpdateRequest.java).

```java

    // Update insurance contract funds
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getFundsResource()
            .update(FundsUpdateRequest request, CallbackWebApi<FundsUpdateResponse> callback);

```

### 7. List insurance contract beneficiaries

You can list all of current users insurance contract beneficiaries by calling the `list` method on [InsurancesContractBeneficiariesResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractBeneficiariesResource.java). 

```java

    // Insurance contract beneficiaries list
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getBeneficiariesResource()
            .list(CallbackWebApi<InsuranceBeneficiariesListResponse> callback);

```

### 8. Update insurance contract beneficiaries

You can update current users insurance contract beneficiaries by calling the `update` method on [InsurancesContractBeneficiariesResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractBeneficiariesResource.java) and giving it payload in object as a parameter. For payload properties please see [InsuranceBeneficiariesUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsuranceBeneficiariesUpdateRequest.java) and check also the response [InsuranceBeneficiariesUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsuranceBeneficiariesUpdateResponse.java).

```java

    // Update insurance contract beneficiaries
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getBeneficiariesResource()
            .update(InsuranceBeneficiariesUpdateRequest request, CallbackWebApi<InsuranceBeneficiariesUpdateResponse> callback);

```

### 9. List insurance contract insurees

You can list all of current users insurance contract insurees by calling the `list` method on [InsurancesContractInsureesResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractInsureesResource.java). 

```java

    // Insurance contract insurees list
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getInsureesResource()
            .list(CallbackWebApi<InsureesListResponse> callback);

```

### 10. List insurance contract payments

You can list all of current users insurance contract payments by calling the `list` method on [InsurancesContractPaymentsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractPaymentsResource.java). 

```java

    // Insurance contract payments list
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getPaymentsResource()
            .list(CallbackWebApi<ContractPaymentsListResponse> callback);

```

### 11. List insurance contract services

You can list all of current users insurance contract services by calling the `list` method on [InsurancesContractServicesResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractServicesResource.java). 

```java

    // Insurance contract services list
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getServicesResource()
            .list(CallbackWebApi<InsuranceServicesListResponse> callback);

```

### 12. Activate risk sports insurance service

You can activate current users risk sport insurance contract by calling the `update` method on [RiskSportsActivationResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/RiskSportsActivationResource.java) and giving it payload in object as a parameter. For payload properties please see [RiskSportsUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/RiskSportsUpdateRequest.java) and check also the response [RiskSportsActivationUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/RiskSportsActivationUpdateResponse.java).

```java

    // Risk sport insurance contract activation
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getServicesResource()
            .getRiskSportsActivationResource()
            .update(RiskSportsUpdateRequest request, CallbackWebApi<RiskSportsActivationUpdateResponse> callback);

```

### 13. Deactivate risk sports insurance service

You can deactivate current users risk sport insurance contract by calling the `update` method on [RiskSportsDeactivationResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/RiskSportsDeactivationResource.java) and giving it payload in object as a parameter. For payload properties please see [RiskSportsUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/RiskSportsUpdateRequest.java) and check also the response [RiskSportsDeactivationUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/RiskSportsDeactivationUpdateResponse.java).

```java

    // Risk sport insurance contract deactivation
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getServicesResource()
            .getRiskSportsDeactivationResource()
            .update(RiskSportsUpdateRequest request, CallbackWebApi<RiskSportsDeactivationUpdateResponse> callback);

```

### 14. List insurance contract events

You can list all of current users insurance contract events by calling the `list` method on [InsurancesContractEventsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractEventsResource.java). 

```java

    // Insurance contract events list
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getEventsResource()
            .list(CallbackWebApi<ContractEventsListResponse> callback);

```

### 15. Get insurance contract tax benefits

You can get insurance contract tax benefits by calling the `get` method on [InsurancesContractTaxBenefitsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractTaxBenefitsResource.java). For complete response see [TaxBenefit](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/TaxBenefit.java).

```java

    // Building contract detail
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
        .withId(Object id)
        .getTaxBenefitsResource()
        .get(CallbackWebApi<TaxBenefit> callback);

```

### 16. List insurance contract strategies

You can list all of current users insurance contract strategies by calling the `list` method on [InsurancesContractStrategiesResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractStrategiesResource.java). 

```java

    // Insurance contract strategies list
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getStrategiesResource()
            .list(CallbackWebApi<ContractStrategiesListResponse> callback);

```

### 17. Update insurance contract transfer

You can update current users insurance contract transfer by calling the `update` method on [InsurancesContractTransferResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsurancesContractTransferResource.java) and giving it payload in object as a parameter. For payload properties please see [InsuranceTransferUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsuranceTransferUpdateRequest.java) and check also the response [InsuranceTransferUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/contracts/insurances/InsuranceTransferUpdateResponse.java).

```java

    // Update insurance contract transfer
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getInsuranceResource().
            .withId(Object id)
            .getTransferResource()
            .update(InsuranceTransferUpdateRequest request, CallbackWebApi<InsuranceTransferUpdateResponse> callback);

```

## C. Loyalty

### 1. Get individual current users insurance contract

You can get detail of the individual current users insurance contract by calling the `get` method on [LoyaltyContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/loyalty/LoyaltyContractsResource.java). For complete response see [Loyalty](../netbanking/src/main/java/cz/csas/netbanking/contracts/loyalty/Loyalty.java).

```java

    // Insurance contract detail
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getLoyaltyResource().
        .withId(Object id)
        .get(CallbackWebApi<Loyalty> callback);

```

## D. Pensions

### 1. List all pensions contracts
You can list all of current users pension contracts by calling the `list` method on [PensionsContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/pensions/PensionsContractsResource.java). 

```java

    // List all pensions contracts
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getPensionsResource().
        .list(PaginatedParameters parameters, CallbackWebApi<PensionsListResponse> callback);

```

### 2. Get individual current users pension contract

You can get detail of the individual current users building contract by calling the `withId` method on [PensionsContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/pensions/PensionsContractsResource.java) with id as a parameter and then calling the get method. For complete response see [Pension](../netbanking/src/main/java/cz/csas/netbanking/contracts/pensions/Pension.java).

```java

    // Pension contract detail
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getPensionsResource().
        .withId(Object id)
        .get(CallbackWebApi<Pension> callback);

```

### 3. Update current users pension contract

You can update current users building contract by calling the `withId` method on [PensionsContractsResource](../netbanking/src/main/java/cz/csas/netbanking/contracts/pensions/PensionsContractsResource.java) with id as a parameter and then calling the update method and giving it payload in object as a parameter. For payload properties please see [PensionUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/contracts/pensions/PensionUpdateRequest.java) and check also the response [PensionUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/contracts/pensions/PensionUpdateResponse.java).

```java

    // Building contract update
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getPensionsResource().
        .withId(Object id)
        .update(PensionUpdateRequest request, CallbackWebApi<PensionUpdateResponse> callback);

```

### 4. Add or change note and mark pensions transaction

Add, change or mark transaction by calling the `update` method on [TransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/TransactionsResource.java) and passing it object with options as a parameter. For payload properties please see [TransactionUpdateRequest](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateRequest.java) and check also the response [TransactionUpdateResponse](../netbanking/src/main/java/cz/csas/netbanking/TransactionUpdateResponse.java).

```java

    // Building contract transaction update
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getPensionsResource().
            .withId(Object id)
            .getTransactionsResource()
            .withId(Object id)
            .update(TransactionUpdateRequest request, CallbackWebApi<TransactionUpdateResponse> callback);

```

### 5. Export pensions transactions history

Export transaction history into signed PDF by calling the `export` method on [TransactionsResource](../netbanking/src/main/java/cz/csas/netbanking/TransactionsResource.java) and passing it object with options as a parameter. See [ExportTransactionsParameters](../netbanking/src/main/java/cz/csas/netbanking/ExportTransactionsParameters.java).

```java

    // Export building contract transactions history
    Netbanking.getInstance().getNetbankingClient().getContractsResource().getPensionsResource().
            .withId(Object id)
            .getTransactionsResource()
            .getExportResource()
            .export(ExportTransactionsParameters parameters, CallbackWebApi<WebApiStream> callback) ;

```
