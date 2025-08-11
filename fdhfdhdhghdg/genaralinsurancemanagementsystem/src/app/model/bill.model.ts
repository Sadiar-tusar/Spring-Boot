

export class BillModel {
    "id"!: number;
    "fire"!: number
    "rsd"!: number;
    "netPremium"!: number;
    "tax"!: number;
    "grossPremium"!: number;

    policies!: {
        "id": number | undefined;
        "date": Date | undefined;
        "bankName": string | undefined;
        "policyholder": string | undefined;
        "address": string | undefined;
        "stockInsured": string | undefined;
        "sumInsured": number | undefined;
        "interestInsured": string | undefined;
        "coverage": string | undefined;
        "location": string | undefined;
        "construction": string | undefined;
        "owner": string | undefined;
        "usedAs": string | undefined;
        "periodFrom": string | undefined;
        "periodTo": string | undefined;
    }

}