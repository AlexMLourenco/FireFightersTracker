<template>
  <div>
    <client-only placeholder="Loading...">
    <div class="mapouter">
      <MglMap 
        :accessToken="accessToken" 
        :mapStyle.sync="mapStyle"
        @load="onMapLoad">

            <MglMarker v-for="(firefighter, index) in firefighters" :key="index" :coordinates.sync="firefighter.gps.coordinates">
              <v-icon slot="marker" color="red">mdi-fire-truck</v-icon>
                <MglPopup>
                  <VCard>
                  <p class="category d-inline-flex font-weight-light">
                  GPS Location
                  </p>
                  <div>Longitude {{firefighter.gps.gps_tag_long}} </div>
                  <div>Latitude {{firefighter.gps.gps_tag_lat}} </div>
                  <p></p>
                  <p class="category d-inline-flex font-weight-light">
                  Environment Data
                  </p>
                  <div>CO {{firefighter.env.co}} </div>
                  <div>            
                    <v-icon
                      class="mr-2"
                      small
                      color="blue"
                    >
                      mdi-temperature-celsius
                    </v-icon>
                    Temperature {{firefighter.env.temp}} 
                  </div>
                  <div>HGT {{firefighter.env.hgt}} </div>
                  <div>NO2 {{firefighter.env.no2}} </div>
                  <div>HUM {{firefighter.env.hum}} </div>
                  <div>LUM {{firefighter.env.lum}} </div>
                  <div>Battery {{firefighter.env.battery}} </div>
                  <div>Heart Rate {{firefighter.hr.hr}}</div>
                  </VCard>
              </MglPopup>
            </MglMarker>

      </MglMap>

    </div>  
    </client-only>
  </div>
</template>

<script>
  import axios from "axios"
  import materialCard from '~/components/material/AppCard'
  import materialChartCard from '~/components/material/AppChartCard'
  import materialStatsCard from '~/components/material/AppStatsCard'

  export default {
    layout: 'dashboard',
    components:{
      materialCard,
      materialChartCard,
      materialStatsCard
    },
    data(){
        return {
          accessToken: 'pk.eyJ1IjoibnVubzc3NzYiLCJhIjoiY2s4cGo3eHJ4MTRnMjNkcXpqaHd5ZjB5cSJ9.pqySVQnXqXakFACfoQkdqQ', // your access token. Needed if you using Mapbox maps
          mapStyle: 'mapbox://styles/mapbox/streets-v11',
          coordinates: [-8.16064851,40.06462326],
          firefighters:[],
          dailySalesChart: {
          data: {
            labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
            series: [
              [12, 17, 7, 17, 23, 18, 38]
            ]
          },
          options: {
            low: 0,
            high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: {
              top: 20,
              right: 0,
              bottom: 0,
              left: 0
            }
          }
        }
        };
    },
    head () {
      return {
        link: [
          {rel: "stylesheet", href: "https://api.tiles.mapbox.com/mapbox-gl-js/v0.53.0/mapbox-gl.css"}
        ],
        meta: [
          { hid: 'description', name: 'description', content: 'My custom description' }
        ]
      }
    },
    methods: {
      async onMapLoad(event) {
        // Here we cathing 'load' map event
        const firefighters = await this.getFirefighters()
        this.firefighters = firefighters;
        this.firefighters.forEach( firefighter => firefighter.gps.coordinates = [firefighter.gps.gps_tag_long,firefighter.gps.gps_tag_lat])
        this.map = event.map;

        this.$store.map = event.map;
        // this.$store.map
        

        const asyncActions = event.component.actions
                
        // const test = await asyncActions.addSource('vr12', {
        //   type: 'image',
        //   url: '/firefighter.png',
        //   coordinates: [
        //       [-8.175346,39.950672],
        //       [-8.630132, 40.645434],
        //       [-8.630132, 40.645900],
        //       [-8.630132, 40.645472]
        //   ]
        // });


        const newParams = await asyncActions.flyTo({
          // center: [40.645427,-8.630132],
          center: [firefighters[0].gps.gps_tag_long,firefighters[0].gps.gps_tag_lat],
          zoom: 17,
          speed: 3
        })
        console.log(newParams)
        /* => {
                center: [30, 30],
                zoom: 9,
                bearing: 9,
                pitch: 7
              }
        */
        
      },
      async getFirefighters(){
        // console.log($this.store.actions.get_firefighters)
        // const res = await this.$store.state.dispatch("get_firefighters")
        try{
          const res = await axios.get(this.getUrl() + '/fighters/all')
          return res.data
        }catch(error){
          console.log(error)
        }
      },
      async fetchFirefighters(){
        try{
          const res = await axios.get(this.getUrl() + '/fighters/all')
          this.firefighters = res.data
          this.firefighters.forEach( firefighter => firefighter.gps.coordinates = [firefighter.gps.gps_tag_long,firefighter.gps.gps_tag_lat])
          // console.log('depois', this.firefighters)
        }catch(error){
          console.log(error)
        }   
      },

      //Url service layer
      getUrl(){
        return "http://localhost:8080"
      }
    },
    computed: {
      firefighterId(){
        
      }
    },
    created(){
      this.fetchFirefighters();
      this.timer = setInterval(this.fetchFirefighters, 3000)
    }
}

</script>

<style scoped>
  .mapouter {
    text-align:right;
    height:100%;
    width:100%;
    position: absolute;
  }
  .gmap_canvas {
    overflow:hidden;
    background:none!important;
    height:100%;
    width:100%;
  }
</style>
