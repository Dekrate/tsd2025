import { IsString, IsNumber, Min } from 'class-validator';


export class CreateAnimalDto {
  @IsString()
  name: string;


  @IsString()
  species: string;


  @IsNumber()
  @Min(1)
  price: number;
}
