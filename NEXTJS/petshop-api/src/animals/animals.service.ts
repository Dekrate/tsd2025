import { Injectable } from '@nestjs/common';
import { CreateAnimalDto } from './dto/create-animal.dto';

@Injectable()
export class AnimalsService {
    private animals: CreateAnimalDto[] = [];


    addAnimal(animal: CreateAnimalDto) {
        this.animals.push(animal);
        return { message: 'Animal added', animal };
    }
    getAllAnimals() {
        return this.animals;
    }

    findByName(name: string) {
        const found = this.animals.find(a => a.name.toLowerCase() === name.toLowerCase());
        return found || { message: 'Animal not found' };
    }

}