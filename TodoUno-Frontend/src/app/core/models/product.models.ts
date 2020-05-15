import { Supplier } from './supplier.models';
import { Category } from './category.model';

export class Product {
    id: number;
    code: string;
    name: string;
    description: string;
    createdAt: string;
    inProducts: number;
    outProducts: number;
    priceUnit: number;
    totalUnit: number;
    existence: number;
    status: string;
    supplierId: Supplier;
    categoryId: Category;
}