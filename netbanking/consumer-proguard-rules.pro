# Netbanking SDK

## accounts
-keep class cz.csas.netbanking.accounts.AccountBalance { *; }
-keep class cz.csas.netbanking.accounts.DirectDebit { *; }
-keep class cz.csas.netbanking.accounts.DirectDebitType { *; }
-keep class cz.csas.netbanking.accounts.AccountNumber { *; }
-keep class cz.csas.netbanking.accounts.Symbols { *; }
-keep class cz.csas.netbanking.accounts.PeriodCycle { *; }
-keep class cz.csas.netbanking.accounts.DirectDebitResponse { *; }
-keep class cz.csas.netbanking.accounts.DirectDebitCreateRequest { *; }
-keep class cz.csas.netbanking.accounts.DirectDebitsListResponse { *; }
-keep class cz.csas.netbanking.accounts.RepaymentsListResponse { *; }
-keep class cz.csas.netbanking.accounts.Repayment { *; }
-keep class cz.csas.netbanking.accounts.ReservationsListResponse { *; }
-keep class cz.csas.netbanking.accounts.Reservation { *; }
-keep class cz.csas.netbanking.accounts.ReservationType { *; }
-keep class cz.csas.netbanking.accounts.ReservationStatus { *; }
-keep class cz.csas.netbanking.accounts.MainAccount { *; }
-keep class cz.csas.netbanking.accounts.OverdraftAmount { *; }
-keep class cz.csas.netbanking.accounts.SubAccount { *; }
-keep class cz.csas.netbanking.accounts.TransferReceiver { *; }
-keep class cz.csas.netbanking.accounts.ChangeAccountSettingsRequest { *; }
-keep class cz.csas.netbanking.accounts.ChangeAccountSettingsResponse { *; }
-keep class cz.csas.netbanking.accounts.AccountServicesListResponse { *; }
-keep class cz.csas.netbanking.accounts.Service { *; }
-keep class cz.csas.netbanking.accounts.AccountsListResponse { *; }
-keep class cz.csas.netbanking.accounts.StandingOrderResponse { *; }
-keep class cz.csas.netbanking.accounts.StandingOrder { *; }
-keep class cz.csas.netbanking.accounts.StandingOrderType { *; }
-keep class cz.csas.netbanking.accounts.StandingOrderSubtype { *; }
-keep class cz.csas.netbanking.accounts.ExecutionMode { *; }
-keep class cz.csas.netbanking.accounts.ExecutionDueMode { *; }
-keep class cz.csas.netbanking.accounts.ExecutionInterval { *; }
-keep class cz.csas.netbanking.accounts.BreakPeriod { *; }
-keep class cz.csas.netbanking.accounts.StoppageMonth { *; }
-keep class cz.csas.netbanking.accounts.StandingOrderCreateRequest { *; }
-keep class cz.csas.netbanking.accounts.StandingOrdersListResponse { *; }
-keep class cz.csas.netbanking.accounts.AccountTransferRequest { *; }
-keep class cz.csas.netbanking.accounts.AccountsTransferType { *; }

## authorization limits
-keep class cz.csas.netbanking.authorizationLimits.AuthorizationLimitsListResponse { *; }
-keep class cz.csas.netbanking.authorizationLimits.AuthorizationLimit { *; }
-keep class cz.csas.netbanking.authorizationLimits.AuthorizationType { *; }
-keep class cz.csas.netbanking.authorizationLimits.ChannelId { *; }
-keep class cz.csas.netbanking.authorizationLimits.ApplicationId { *; }

## authorization token

## budgets
-keep class cz.csas.netbanking.budgets.AuthorizationLimitsListResponse { *; }
-keep class cz.csas.netbanking.budgets.BudgetsListResponse { *; }
-keep class cz.csas.netbanking.budgets.Budget { *; }
-keep class cz.csas.netbanking.budgets.Category { *; }
-keep class cz.csas.netbanking.budgets.CategoryId { *; }
-keep class cz.csas.netbanking.budgets.BudgetsUpdateRequest { *; }

## bundles
-keep class cz.csas.netbanking.bundles.BundleCreateRequest { *; }
-keep class cz.csas.netbanking.bundles.BundleItem { *; }
-keep class cz.csas.netbanking.bundles.Bundle { *; }

## cards
-keep class cz.csas.netbanking.cards.Card { *; }
-keep class cz.csas.netbanking.cards.CardAccountLimits { *; }
-keep class cz.csas.netbanking.cards.CardAction { *; }
-keep class cz.csas.netbanking.cards.CardActionRequest { *; }
-keep class cz.csas.netbanking.cards.CardActionResponse { *; }
-keep class cz.csas.netbanking.cards.CardCharacteristic { *; }
-keep class cz.csas.netbanking.cards.CardDelivery { *; }
-keep class cz.csas.netbanking.cards.CardDeliveryMode { *; }
-keep class cz.csas.netbanking.cards.CardLimit { *; }
-keep class cz.csas.netbanking.cards.CardLimitPeriod { *; }
-keep class cz.csas.netbanking.cards.CardLimitsListResponse { *; }
-keep class cz.csas.netbanking.cards.CardLimitType { *; }
-keep class cz.csas.netbanking.cards.CardMainAccount { *; }
-keep class cz.csas.netbanking.cards.CardProvider { *; }
-keep class cz.csas.netbanking.cards.CardsListResponse { *; }
-keep class cz.csas.netbanking.cards.CardState { *; }
-keep class cz.csas.netbanking.cards.CardTransferRequest { *; }
-keep class cz.csas.netbanking.cards.CardTransferType { *; }
-keep class cz.csas.netbanking.cards.CardType { *; }
-keep class cz.csas.netbanking.cards.ChangeCardDeliverySettingsRequest { *; }
-keep class cz.csas.netbanking.cards.ChangeCardDeliverySettingsResponse { *; }
-keep class cz.csas.netbanking.cards.ChangeCardLimitsRequest { *; }
-keep class cz.csas.netbanking.cards.ChangeCardLimitsResponse { *; }
-keep class cz.csas.netbanking.cards.ChangeCardSettingsRequest { *; }
-keep class cz.csas.netbanking.cards.ChangeCardSettingsResponse { *; }
-keep class cz.csas.netbanking.cards.Confirmation { *; }
-keep class cz.csas.netbanking.cards.LockReason { *; }
-keep class cz.csas.netbanking.cards.PayUpCreditCardRequest { *; }
-keep class cz.csas.netbanking.cards.PayUpCreditCardResponse { *; }
-keep class cz.csas.netbanking.cards.SecureSettings { *; }
-keep class cz.csas.netbanking.cards.SecureSettingsStatus { *; }
-keep class cz.csas.netbanking.cards.Sender { *; }

## authorization contacts
-keep class cz.csas.netbanking.contacts.Contact { *; }
-keep class cz.csas.netbanking.contacts.ContactAddress { *; }
-keep class cz.csas.netbanking.contacts.ContactAddressType { *; }
-keep class cz.csas.netbanking.contacts.ContactPhone { *; }
-keep class cz.csas.netbanking.contacts.ContactPhoneType { *; }
-keep class cz.csas.netbanking.contacts.ContactsListResponse { *; }
-keep class cz.csas.netbanking.contacts.ContactType { *; }
-keep class cz.csas.netbanking.contacts.Email { *; }
-keep class cz.csas.netbanking.contacts.EmailType { *; }

## contracts
# buildings
-keep class cz.csas.netbanking.contracts.buildings.BuildingsContractUpdateRequest { *; }
-keep class cz.csas.netbanking.contracts.buildings.BuildingsContractStatus { *; }
-keep class cz.csas.netbanking.contracts.buildings.BuildingsContract { *; }
-keep class cz.csas.netbanking.contracts.buildings.BuildingSaving { *; }
-keep class cz.csas.netbanking.contracts.buildings.BuildingAccountType { *; }
-keep class cz.csas.netbanking.contracts.buildings.Loan { *; }
-keep class cz.csas.netbanking.contracts.buildings.BuildingsListResponse { *; }
-keep class cz.csas.netbanking.contracts.buildings.BuildingsContractUpdateResponse { *; }

# insurances
-keep class cz.csas.netbanking.contracts.insurances.ContractPaymentsListResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.ContractPayment { *; }
-keep class cz.csas.netbanking.contracts.insurances.ContractEventState { *; }
-keep class cz.csas.netbanking.contracts.insurances.ContractEventsListResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.ContractEvent { *; }
-keep class cz.csas.netbanking.contracts.insurances.EmployerBenefit { *; }
-keep class cz.csas.netbanking.contracts.insurances.ContractStrategyType { *; }
-keep class cz.csas.netbanking.contracts.insurances.ContractStrategyGroup { *; }
-keep class cz.csas.netbanking.contracts.insurances.ContractStrategy { *; }
-keep class cz.csas.netbanking.contracts.insurances.ContractStrategiesListResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.FundsUpdateRequest { *; }
-keep class cz.csas.netbanking.contracts.insurances.FundsListResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.FundInfo { *; }
-keep class cz.csas.netbanking.contracts.insurances.Fund { *; }
-keep class cz.csas.netbanking.contracts.insurances.EmployerBenefitType { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceBeneficiariesListResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.Insurance { *; }
-keep class cz.csas.netbanking.contracts.insurances.Indemnity { *; }
-keep class cz.csas.netbanking.contracts.insurances.Immobilization { *; }
-keep class cz.csas.netbanking.contracts.insurances.FundsUpdateResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceDetail { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceBeneficiaryType { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceBeneficiary { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceBeneficiariesUpdateResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceBeneficiariesUpdateRequest { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceStatus { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceServiceState { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceServicesListResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceService { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceListResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceUpdateRequest { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceType { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceTransferUpdateResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceTransferUpdateRequest { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceTransferType { *; }
-keep class cz.csas.netbanking.contracts.insurances.Interval { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsureeType { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsureesListResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.BuildingsContractUpdateResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.Insuree { *; }
-keep class cz.csas.netbanking.contracts.insurances.InsuranceUpdateResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.PaymentTemplateType { *; }
-keep class cz.csas.netbanking.contracts.insurances.PaymentTemplate { *; }
-keep class cz.csas.netbanking.contracts.insurances.LifeDetail { *; }
-keep class cz.csas.netbanking.contracts.insurances.Life { *; }
-keep class cz.csas.netbanking.contracts.insurances.InvestmentProgram { *; }
-keep class cz.csas.netbanking.contracts.insurances.RiskSportsUpdateRequest { *; }
-keep class cz.csas.netbanking.contracts.insurances.RiskSportsDeactivationUpdateResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.RiskSportsActivationUpdateResponse { *; }
-keep class cz.csas.netbanking.contracts.insurances.Risk { *; }
-keep class cz.csas.netbanking.contracts.insurances.PaymentType { *; }
-keep class cz.csas.netbanking.contracts.insurances.TaxBenefit { *; }
-keep class cz.csas.netbanking.contracts.insurances.StrategyFund { *; }

# loyalty
-keep class cz.csas.netbanking.contracts.loyalty.LoyaltyState { *; }
-keep class cz.csas.netbanking.contracts.loyalty.Loyalty { *; }

# pensions
-keep class cz.csas.netbanking.contracts.pensions.PensionAgreed { *; }
-keep class cz.csas.netbanking.contracts.pensions.Pension { *; }
-keep class cz.csas.netbanking.contracts.pensions.Entitlement { *; }
-keep class cz.csas.netbanking.contracts.pensions.Contribution { *; }
-keep class cz.csas.netbanking.contracts.pensions.Beneficiary { *; }
-keep class cz.csas.netbanking.contracts.pensions.PensionUpdateResponse { *; }
-keep class cz.csas.netbanking.contracts.pensions.PensionUpdateRequest { *; }
-keep class cz.csas.netbanking.contracts.pensions.PensionSubtype { *; }
-keep class cz.csas.netbanking.contracts.pensions.PensionStatus { *; }
-keep class cz.csas.netbanking.contracts.pensions.PensionsListResponse { *; }
-keep class cz.csas.netbanking.contracts.pensions.Supplementary { *; }
-keep class cz.csas.netbanking.contracts.pensions.Strategy { *; }
-keep class cz.csas.netbanking.contracts.pensions.SavingTime { *; }
-keep class cz.csas.netbanking.contracts.pensions.ProductAccount { *; }

## goals
-keep class cz.csas.netbanking.goals.GoalsUpdateRequest { *; }
-keep class cz.csas.netbanking.goals.GoalsListResponse { *; }
-keep class cz.csas.netbanking.goals.Goal { *; }

## messages
-keep class cz.csas.netbanking.messages.MessagesListResponse { *; }
-keep class cz.csas.netbanking.messages.Message { *; }
-keep class cz.csas.netbanking.messages.Attachment { *; }
-keep class cz.csas.netbanking.messages.MessageUpdateRequest { *; }

## orders
-keep class cz.csas.netbanking.orders.DomesticPaymentCreateResponse { *; }
-keep class cz.csas.netbanking.orders.DomesticPaymentCreateRequest { *; }
-keep class cz.csas.netbanking.orders.ChannelId { *; }
-keep class cz.csas.netbanking.orders.AuthorizationType { *; }
-keep class cz.csas.netbanking.orders.ApplicationId { *; }
-keep class cz.csas.netbanking.orders.MobilePaymentResponse { *; }
-keep class cz.csas.netbanking.orders.MobilePaymentRequest { *; }
-keep class cz.csas.netbanking.orders.Info { *; }
-keep class cz.csas.netbanking.orders.DomesticPaymentUpdateResponse { *; }
-keep class cz.csas.netbanking.orders.DomesticPaymentUpdateRequest { *; }
-keep class cz.csas.netbanking.orders.PaymentBookingDateResponse { *; }
-keep class cz.csas.netbanking.orders.PaymentBookingDateRequest { *; }
-keep class cz.csas.netbanking.orders.Payment { *; }
-keep class cz.csas.netbanking.orders.MobilePaymentType { *; }
-keep class cz.csas.netbanking.orders.MobilePaymentSender { *; }
-keep class cz.csas.netbanking.orders.PaymentOrderType { *; }
-keep class cz.csas.netbanking.orders.PaymentOrderPriority { *; }
-keep class cz.csas.netbanking.orders.PaymentLimitsListResponse { *; }
-keep class cz.csas.netbanking.orders.PaymentLimit { *; }
-keep class cz.csas.netbanking.orders.PaymentCategory { *; }
-keep class cz.csas.netbanking.orders.Symbols { *; }
-keep class cz.csas.netbanking.orders.PaymentStateDetail { *; }
-keep class cz.csas.netbanking.orders.PaymentState { *; }
-keep class cz.csas.netbanking.orders.PaymentsListResponse { *; }

## phone numbers
-keep class cz.csas.netbanking.phoneNumbers.PhoneNumberUpdateRequest { *; }
-keep class cz.csas.netbanking.phoneNumbers.PhoneNumbersListResponse { *; }
-keep class cz.csas.netbanking.phoneNumbers.PhoneNumberCreateRequest { *; }
-keep class cz.csas.netbanking.phoneNumbers.PhoneNumber { *; }

## plugins
-keep class cz.csas.netbanking.plugins.PluginUpdateResponse { *; }
-keep class cz.csas.netbanking.plugins.PluginUpdateRequest { *; }
-keep class cz.csas.netbanking.plugins.PluginsListResponse { *; }
-keep class cz.csas.netbanking.plugins.Plugin { *; }
-keep class cz.csas.netbanking.plugins.PeriodOfCharging { *; }
-keep class cz.csas.netbanking.plugins.TimeOfCharging { *; }
-keep class cz.csas.netbanking.plugins.StandardFee { *; }

# profile
-keep class cz.csas.netbanking.profile.LastLoginListResponse { *; }
-keep class cz.csas.netbanking.profile.LastLoginInfo { *; }
-keep class cz.csas.netbanking.profile.Profile { *; }
-keep class cz.csas.netbanking.profile.Gender { *; }
-keep class cz.csas.netbanking.profile.MarketingInfoAcceptance { *; }

## promotions
-keep class cz.csas.netbanking.promotions.DisplayType { *; }
-keep class cz.csas.netbanking.promotions.CardDesign { *; }
-keep class cz.csas.netbanking.promotions.ButtonDesign { *; }
-keep class cz.csas.netbanking.promotions.ActionType { *; }
-keep class cz.csas.netbanking.promotions.Action { *; }
-keep class cz.csas.netbanking.promotions.Promotion { *; }
-keep class cz.csas.netbanking.promotions.ProductCode { *; }
-keep class cz.csas.netbanking.promotions.InfoItem { *; }
-keep class cz.csas.netbanking.promotions.ExecutedAction { *; }
-keep class cz.csas.netbanking.promotions.DisplayTypeKind { *; }
-keep class cz.csas.netbanking.promotions.PromotionsListResponse { *; }
-keep class cz.csas.netbanking.promotions.PromotionActionCreateResponse { *; }
-keep class cz.csas.netbanking.promotions.PromotionActionCreateRequest { *; }

## securities
-keep class cz.csas.netbanking.securities.SecurityUpdateRequest { *; }
-keep class cz.csas.netbanking.securities.SecurityType { *; }
-keep class cz.csas.netbanking.securities.Security { *; }
-keep class cz.csas.netbanking.securities.SecuritiesListResponse { *; }
-keep class cz.csas.netbanking.securities.ProductGroup { *; }
-keep class cz.csas.netbanking.securities.SubSecAccountTitle { *; }
-keep class cz.csas.netbanking.securities.SubSecAccount { *; }
-keep class cz.csas.netbanking.securities.SecurityUpdateResponse { *; }

## services

## settings
-keep class cz.csas.netbanking.settings.SettingsUpdateResponse { *; }
-keep class cz.csas.netbanking.settings.SettingsUpdateRequest { *; }
-keep class cz.csas.netbanking.settings.Settings { *; }

## templates
-keep class cz.csas.netbanking.templates.TemplatesListResponse { *; }
-keep class cz.csas.netbanking.templates.Template { *; }
-keep class cz.csas.netbanking.templates.OrderCategory { *; }

## common
-keep class cz.csas.netbanking.Language { *; }
-keep class cz.csas.netbanking.Format { *; }
-keep class cz.csas.netbanking.Amount { *; }
-keep class cz.csas.netbanking.Address { *; }
-keep class cz.csas.netbanking.AccountNumber { *; }
-keep class cz.csas.netbanking.StatementsListResponse { *; }
-keep class cz.csas.netbanking.Statement { *; }
-keep class cz.csas.netbanking.ServicesListResponse { *; }
-keep class cz.csas.netbanking.Service { *; }
-keep class cz.csas.netbanking.Periodicity { *; }
-keep class cz.csas.netbanking.TransferResponse { *; }
-keep class cz.csas.netbanking.TransactionUpdateResponse { *; }
-keep class cz.csas.netbanking.TransactionUpdateRequest { *; }
-keep class cz.csas.netbanking.TransactionField { *; }
-keep class cz.csas.netbanking.Transaction { *; }
