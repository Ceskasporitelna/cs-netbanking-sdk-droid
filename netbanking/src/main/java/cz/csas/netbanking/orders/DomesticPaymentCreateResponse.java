package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.apiquery.CsSignUrl;

/**
 * The type Domestic order response.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
@CsSignUrl(signUrl = "/../{id}")
public class DomesticPaymentCreateResponse extends Payment {
}
