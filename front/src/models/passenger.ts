// src/types/passengerTypes.ts
export type DocEnum = "CC" | "TI" | "PE" | "RC";

export type GenderEnum = "MEN" | "WOMEN" | "OTHER";

export interface PassengerRequestDTO{
  bookingId: number;
  name: string;
  docType: DocEnum;
  numberDoc: string;
  birth: string;
  gender: GenderEnum;
  infant: boolean;
  phone: string;
  email: string;
  seatNumber: number;
}
