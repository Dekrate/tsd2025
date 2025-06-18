import { Controller, Post, Get, Body, Param } from '@nestjs/common';
import { CreateAnimalDto } from './dto/create-animal.dto';
import { AnimalsService } from './animals.service';

@Controller('animals')
export class AnimalsController {
    constructor(private readonly animalsService: AnimalsService) { }

    @Post()
    addAnimal(@Body() animal: CreateAnimalDto) {
        return this.animalsService.addAnimal(animal);
    }

    @Get()
    getAnimals() {
        return this.animalsService.getAllAnimals();
    }

    @Get(':name')
    getByName(@Param('name') name: string) {
        return this.animalsService.findByName(name);
    }
}