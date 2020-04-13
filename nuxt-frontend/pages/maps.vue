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

      <MglMap :accessToken="accessToken" :mapStyle.sync="mapStyle" >
      <!-- <MglMarker :coordinates="coordinates" color="blue" /> -->


        <!-- <div v-for="fire in fires" :key="fire.id">
          <MglMarker :coordinates="fire.coordinates">
            
            <v-icon slot="marker">mdi-fire</v-icon>
          </MglMarker>
        </div> -->

      </MglMap>

    </div>
    </client-only>
  </div>
</template>

<script>
  export default {
    layout: 'dashboard',
    data(){
        return {
          accessToken: 'pk.eyJ1IjoibnVubzc3NzYiLCJhIjoiY2s4cGo3eHJ4MTRnMjNkcXpqaHd5ZjB5cSJ9.pqySVQnXqXakFACfoQkdqQ', // your access token. Needed if you using Mapbox maps
          mapStyle: 'mapbox://styles/mapbox/streets-v11',
          coordinates: [-111.549668, 39.014],
          fires: {
            id: '1', coordinates: [-111.549668, 39.014],
            id: '2', coordinates: [-111.549677, 39.028],
            id: '3', coordinates: [-111.549677, 39.036],
          }
        };
    }
  }


</script>

<style>
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
