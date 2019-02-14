package com.mastercode.salvago.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Localbase {


    public List<String> CompanyType(){
        List<String> types = new ArrayList<>();
        types.add("Restaurante");
        types.add("Hospedaje");
        types.add("Sitio Turistico");
        types.add("Centro de servicio");
        types.add("Tienda");
        return types;
    }

    public Map<Integer,String> SalvadorDepartaments(){
        Map<Integer,String> departaments = new HashMap<Integer,String>();
        departaments.put(1, "Ahuachapan");
        departaments.put(2, "Santa Ana");
        departaments.put(3, "Sonsonate");
        departaments.put(4, "Chalatenango");
        departaments.put(5, "La Libertad");
        departaments.put(6, "San Salvador");
        departaments.put(7, "Cuscatlán");
        departaments.put(8, "Cabañas");
        departaments.put(9, "La Paz");
        departaments.put(10, "San Vicente");
        departaments.put(11, "Usulután");
        departaments.put(12, "San Miguel");
        departaments.put(13, "Morazán");
        departaments.put(14, "La Unión");
        return departaments;
    }

    public Map<Integer,String> getCitiesByDepartament(int depa){
        Map<Integer,String> cities = new HashMap<Integer,String>();
        int min = 0;
        int max = 0;
        switch (depa){
            case 1:
                min = 1; max = 12;
                break;
            case 2:
                min = 13; max = 25;
                break;
            case 3:
                min = 26; max = 41;
                break;
        }

        for (int i = min; i <= max; i++){
            String c = SalvadorCities().get(i);
            cities.put(i,c);
        }

        return cities;
    }


    public Map<Integer,String> SalvadorCities(){

        Map<Integer,String> cities = new HashMap<Integer,String>();

        //DEPARTAMENTO DE AHUACHAPÁN
        cities.put(1,"Ahuachapán");
        cities.put(2,"Apaneca");
        cities.put(3,"Atiquizaya");
        cities.put(4,"Concepcion de Ataco");
        cities.put(5,"El refugio");
        cities.put(6,"Guaymango");
        cities.put(7,"Jujutla");
        cities.put(8,"San Francisco Menéndez");
        cities.put(9,"San Lorenzo");
        cities.put(10,"San Pedro Puxtla");
        cities.put(11,"Tacuba");
        cities.put(12,"Turín");

        //DEPARTAMENTO DE SANTA ANA
        cities.put(13,"Candelaria de la Frontera");
        cities.put(14,"Chalchuapa");
        cities.put(15,"Coatepeque");
        cities.put(16,"El Congo");
        cities.put(17,"El Porvenir");
        cities.put(18,"Masahuat");
        cities.put(19,"Metapán");
        cities.put(20,"San Antonio Pajonal");
        cities.put(21,"San Sebastián Salitrillo");
        cities.put(22,"Santa Ana");
        cities.put(23,"Santa Rosa Guachipilín");
        cities.put(24,"Santiago de la Frontera");
        cities.put(25,"Taxistepeque");

        //DEPARTAMENTO DE SONSONATE
        cities.put(26,"Acajutla");
        cities.put(27,"Armenia");
        cities.put(28,"Caluco");
        cities.put(29,"Cuisnahuat");
        cities.put(30,"Izalco");
        cities.put(31,"Juayúa");
        cities.put(32,"Nahuizalco");
        cities.put(33,"Nahulingo");
        cities.put(34,"Solcoatitán");
        cities.put(35,"San Antonio del Monte");
        cities.put(36,"San julián");
        cities.put(37,"San Catarina Masahuat");
        cities.put(38,"Santa Isabel Ishuatán");
        cities.put(39,"Santo Domingo Guzmán");
        cities.put(40,"Sonsonate");
        cities.put(41,"Sonzacate");

        //DEPARTAMENTO DE CHALATENANGO
        cities.put(42,"Agua Caliente");
        cities.put(43,"Arcatao");
        cities.put(44,"Azacualpa");
        cities.put(45,"Chalatenango");
        cities.put(46,"Comalapa");
        cities.put(47,"Citalá");
        cities.put(48,"Concepción Quezaltepeque");
        cities.put(49,"Dulce Nombre de María");
        cities.put(50,"El Carrizal");
        cities.put(51,"El Paraíso");
        cities.put(52,"La Laguna");
        cities.put(53,"La Palma");
        cities.put(54,"La Reina");
        cities.put(55,"Las Vueltas");
        cities.put(56,"Nueva Concepción");
        cities.put(57,"Nueva Trinidad");
        cities.put(58,"Nombre de Jesús");
        cities.put(59,"Ojos de Agua");
        cities.put(60,"Potonico");
        cities.put(61,"San Antonio de la Cruz");
        cities.put(62,"San Antonio los Ranchos");
        cities.put(63,"San Fernando");
        cities.put(64,"San Francisco Lempa");
        cities.put(65,"San Francisco Morazán");
        cities.put(66,"San Ignacio");
        cities.put(67,"San Isidro Labrador");
        cities.put(68,"San José Cancasque");
        cities.put(69,"San José Las Flores");
        cities.put(70,"San Luis del Carmen");
        cities.put(71,"San Miguel de Mercedes");
        cities.put(72,"San Rafael");
        cities.put(73,"Santa Rita");
        cities.put(74,"Tejutla");

        return cities;
    }


}
