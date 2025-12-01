import { Component, OnInit } from '@angular/core';
import { AxiosService } from 'src/app/service/axios.service';

@Component({
  selector: 'app-debug-content',
  templateUrl: './debug-content.component.html',
  styleUrls: ['./debug-content.component.css']
})
export class DebugContentComponent implements OnInit {

  decodedClaimsInfo: any;

  constructor(private axiosService: AxiosService) { }

  ngOnInit(): void {
    this.requestDebugInfo();
  }

  requestDebugInfo(): void {
    this.axiosService.request(
      "GET",
      "/debug",
      null,
    ).then(response => {
      const claims = response.data
      //convery iat and iss to date
      Object.keys(claims).forEach(key => {
        if(key==='iat' || key ==='exp'){
          claims[key]= new Date(claims[key]* 1000).toLocaleString();
        }
      });
      this.decodedClaimsInfo = claims;
      console.log(this.decodedClaimsInfo);
    }).catch(error=>{
      console.error("Failed to get token info: " + error)
    });
  }
}
