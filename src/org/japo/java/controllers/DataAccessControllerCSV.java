/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.controllers;

import org.japo.java.models.Model;
import org.japo.java.interfaces.IDataAccessController;
import org.japo.java.libraries.UtilesCSV;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class DataAccessControllerCSV implements IDataAccessController {

    // Fichero [CSV] > Modelo
    @Override
    public void importarModelo(Model model, String fichero) throws Exception {
        // Importar Items CSV
        String[] items = UtilesCSV.importarItemsCSV(fichero);

        // Lista de Items > Modelo
        convertirArrayModelo(items, model);        
    }

    // Modelo > Fichero [CSV]
    @Override
    public void exportarModelo(Model model, String fichero) throws Exception {
        // Items
        String[] items = new String[Model.NUM_ITEMS];

        // Modelo > Lista de Items
        convertirModeloArray(model, items);

        // Exportar Items CSV
        UtilesCSV.exportarItemsCSV(items, fichero);
    }

    // Modelo > Array Items
    public void convertirModeloArray(Model modelo, String[] items) {
        // Modelo > Array Items
        items[Model.POS_TEXTO] = modelo.getTexto();
        items[Model.POS_FAMILIA] = modelo.getFamilia();
        items[Model.POS_NEGRITA] = modelo.isNegrita() + "";
        items[Model.POS_CURSIVA] = modelo.isCursiva() + "";
        items[Model.POS_TALLA] = modelo.getTalla() + "";
        items[Model.POS_FRENTE_R] = modelo.getFrenteR() + "";
        items[Model.POS_FRENTE_V] = modelo.getFrenteV() + "";
        items[Model.POS_FRENTE_A] = modelo.getFrenteA() + "";
        items[Model.POS_FONDO_R] = modelo.getFondoR() + "";
        items[Model.POS_FONDO_V] = modelo.getFondoV() + "";
        items[Model.POS_FONDO_A] = modelo.getFondoA() + "";
    }

    // Array Items > Modelo
    public void convertirArrayModelo(String[] items, Model modelo) {
        // Texto
        modelo.setTexto(items[Model.POS_TEXTO]);

        // Familia
        modelo.setFamilia(items[Model.POS_FAMILIA]);

        // Estilo
        modelo.setNegrita(items[Model.POS_NEGRITA].equals("true"));
        modelo.setCursiva(items[Model.POS_CURSIVA].equals("true"));

        // Talla
        try {
            modelo.setTalla(Integer.parseInt(items[Model.POS_TALLA]));
        } catch (NumberFormatException e) {
            modelo.setTalla(Model.DEF_TALLA);
        }

        // Frente
        try {
            modelo.setFrenteR(Integer.parseInt(items[Model.POS_FRENTE_R]));
            modelo.setFrenteV(Integer.parseInt(items[Model.POS_FRENTE_V]));
            modelo.setFrenteA(Integer.parseInt(items[Model.POS_FRENTE_A]));
        } catch (NumberFormatException e) {
            modelo.setFrenteR(Model.DEF_FRENTE_R);
            modelo.setFrenteV(Model.DEF_FRENTE_V);
            modelo.setFrenteA(Model.DEF_FRENTE_A);
        }

        // Fondo
        try {
            modelo.setFondoR(Integer.parseInt(items[Model.POS_FONDO_R]));
            modelo.setFondoV(Integer.parseInt(items[Model.POS_FONDO_V]));
            modelo.setFondoA(Integer.parseInt(items[Model.POS_FONDO_A]));
        } catch (NumberFormatException e) {
            modelo.setFondoR(Model.DEF_FONDO_R);
            modelo.setFondoV(Model.DEF_FONDO_V);
            modelo.setFondoA(Model.DEF_FONDO_A);
        }
    }
}
