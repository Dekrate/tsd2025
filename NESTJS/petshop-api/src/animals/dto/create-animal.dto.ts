import { IsString, IsNumber, Min } from 'class-validator';
import { IsIn } from 'class-validator';

export class CreateAnimalDto {
  @IsString()
  name: string;




  @IsIn(['dog', 'cat', 'hamster', 'parrot', 'rabbit'])
  species: string;
  


  @IsNumber()
  @Min(1)
  price: number;
}
