<template>
  <div>
    <client-only placeholder="Loading...">
    <div class="mapouter">
      <!-- 
        
        <div class="gmap_canvas">

        <iframe
          id="gmap_canvas"
          width="100%"
          height="100%"
          src="https://maps.google.com/maps?width=700&amp;height=440&amp;hl=en&amp;q=Lisbon%2C%Portugal+(Titre)&amp;ie=UTF8&amp;t=&amp;z=10&amp;iwloc=B&amp;output=embed"
          frameborder="0"
          scrolling="no"
          marginheight="0"
          marginwidth="0"
        />
      </div> 
      
      -->

      <MglMap 
        :accessToken="accessToken" 
        :mapStyle.sync="mapStyle"
        @load="onMapLoad">
        <MglMarker :coordinates.sync="coordinates">
            <v-icon slot="marker" color="red">mdi-fire</v-icon>
            <!-- <v-icon slot="marker" color="red">mdi-fire-truck</v-icon> -->
        </MglMarker>


        <!-- <div v-for="fire in fires" :key="fire.id">
          <MglMarker :coordinates="fire.coordinates">
            
            <v-icon slot="marker">mdi-fire</v-icon>
          </MglMarker>
        </div> -->

        <!-- <div v-for="fire in fires" :key="fire.id">
          <MglMarker :coordinates.sync="fire.coordinates">
            <v-icon slot="marker" color="red">mdi-fire</v-icon>
          </MglMarker>
        </div> -->

      </MglMap>

    </div>  
    </client-only>
  </div>
</template>

<script>
  import axios from "axios"
  export default {
    layout: 'dashboard',
    data(){
        return {
          accessToken: 'pk.eyJ1IjoibnVubzc3NzYiLCJhIjoiY2s4cGo3eHJ4MTRnMjNkcXpqaHd5ZjB5cSJ9.pqySVQnXqXakFACfoQkdqQ', // your access token. Needed if you using Mapbox maps
          mapStyle: 'mapbox://styles/mapbox/streets-v11',
          coordinates: [-8.630132,40.645427],
          fires: [
            {id: '1', coordinates: [-8.175346, 39.950672]},
            {id: '2', coordinates: [-8.630132, 40.645472]},
            {id: '3', coordinates: [-8.630132, 40.645489]},
          ]
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
        this.map = event.map;
        this.$store.map = event.map;

        const firefighter = this.getFirefighters()

        const asyncActions = event.component.actions
        

        const newParams = await asyncActions.flyTo({
          // center: [40.645427,-8.630132],
          center: [-8.630132,40.645427],
          zoom: 7,
          speed: 2
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
        // console.log(res)
      },
      getUrl(){
        return "http://localhost:8080/fighters"
      }
    },
    computed: {
      firefighersState(){
        
      }
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
