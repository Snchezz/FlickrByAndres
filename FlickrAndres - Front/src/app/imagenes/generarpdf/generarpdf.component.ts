import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ImagenService } from '../imagen.service';
import { Imagenes } from '../imagenes/imagenes.module';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-generarpdf',
  templateUrl: './generarpdf.component.html',
  styleUrls: ['./generarpdf.component.css']
})
export class GenerarpdfComponent implements OnInit {

  tutoriales?: Imagenes[];
  tutorialActual: Imagenes = {};
  indiceActual = -1;
  titulo = '';


  constructor(private imagenService: ImagenService, private title:Title) { }

  ngOnInit(): void {
    this.recuperarTutoriales();
    this.refrescarLista();
    this.title.setTitle("Flickr Andres - Generar PDF")
  }

  recuperarTutoriales(): void {
    this.imagenService.getTodosTutoriales()
      .subscribe({
        next: (data) => {
          this.tutoriales = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refrescarLista(): void {
    this.recuperarTutoriales();
    this.tutorialActual = {};
    this.indiceActual = -1;
  }

 
  public openPDF(): void {
    // Seleccionamos la informacion que va a coger, mediante el ID
    let DATA: any = document.getElementById('htmlData');
    html2canvas(DATA).then((canvas) => {
      // Seleccionamos la altura y anchura
      let fileWidth = 208;
      let fileHeight = (canvas.height * fileWidth) / canvas.width;
      const FILEURI = canvas.toDataURL('image/png');
      let PDF = new jsPDF('p', 'mm', 'a4');
      let position = 0;
      // Añadimos una imagen 
      PDF.addImage(FILEURI, 'PNG', 0, position, fileWidth, fileHeight);
      // Asignamos el tamaño de fuente
      PDF.setFontSize(20)
      // Asignamos un texto
      PDF.text('Este proyecto ha sido realizado con\nSpring y Angular, por Andrés\nSánchez Vera', 50, 130)
      var img = new Image()
      img.src = 'assets/flickrbysanchezblack.png'
      PDF.addImage(img, 'png', 10, 200, 180, 90)
      // Asignamos el metodo para guardar, y el nombre del documento
      PDF.save('informe-imagenes-andres-sanchez.pdf');
    });
  }
}
