package com.example.gerenteapp;

public class Mahaila {
        private int id;
        private int mahaila_zenbakia;
        private int eserlekuak;
        private boolean habilitado;


        public Mahaila(int id, int mahaila_zenbakia, int eserlekuak, boolean habilitado) {
                this.id = id;
                this.mahaila_zenbakia = mahaila_zenbakia;
                this.eserlekuak = eserlekuak;
                this.habilitado = habilitado;
        }


        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getMahaila_zenbakia() {
                return mahaila_zenbakia;
        }

        public void setMahaila_zenbakia(int mahaila_zenbakia) {
                this.mahaila_zenbakia = mahaila_zenbakia;
        }

        public int getEserlekuak() {
                return eserlekuak;
        }

        public void setEserlekuak(int eserlekuak) {
                this.eserlekuak = eserlekuak;
        }

        public boolean isHabilitado() {
                return habilitado;
        }

        public void setHabilitado(boolean habilitado) {
                this.habilitado = habilitado;
        }

        @Override
        public String toString() {
                return "Mahaila{" +
                        "id=" + id +
                        ", mahaila_zenbakia=" + mahaila_zenbakia +
                        ", eserlekuak=" + eserlekuak +
                        ", habilitado=" + habilitado +
                        '}';
        }
}
